package app.yoda.huddl.huddlimageuploaderdownloader

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.lifecycle.MutableLiveData
import app.yoda.huddl.huddlimageuploaderdownloader.models.UploadFormImage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadOneByOneManager @Inject constructor(val context: Context, val uploadSingleImageManager: UploadSingleImageManager) : UploadImageInteractor {

//    lateinit var uploadSingleImageManager: UploadSingleImageManager
    var currentImageUploadProgressLiveData = MutableLiveData<StatusOfUpload>()
    var currentUploadingIndex = 0
    var isUploadAllowed = true
    var cachePath: File? = null
    lateinit var selectedImages: ArrayList<UploadFormImage>
//    lateinit var context: Context

//    constructor(context: Context) {
//        this.context = context
//    }

//    fun initialize(context: Context)
//    {
//        this.context = context
//    }

    fun startUploadQueue(
        selectedImages: ArrayList<UploadFormImage>,
        indexToStartFrom: Int
    ) {
        this.selectedImages = selectedImages
        currentUploadingIndex = indexToStartFrom
        if (isUploadAllowed) {
            selectedImages.let { images ->
                if (!images.isNullOrEmpty()) {
                    val formImage = images[indexToStartFrom]
                    if (!formImage.is_uploaded) {
                        formImage.getUri()?.let {
                            uploadImageToServer(
                                it,
                                uploadSingleImageManager,
                                this,
                                formImage,
                                currentImageUploadProgressLiveData,
                                indexToStartFrom,
                                selectedImages
                            )
                        }
                    }
                }
            }
        }
    }

    private fun uploadImageToServer(
        contentUri: Uri,
        uploadSingleImageManager: UploadSingleImageManager,
        uploadImageInteractor: UploadImageInteractor,
        img: UploadFormImage,
        currentImageProgressLiveData: MutableLiveData<StatusOfUpload>,
        index: Int,
        selectedImages: ArrayList<UploadFormImage>
    ) {
        var newFile: File? = null
        val cursor =
            context.contentResolver.query(contentUri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
        val fileName = nameIndex?.let { cursor.getString(it) }
        try {
            val isSaved = saveImage(getBitmapFromUri(contentUri), fileName)
            if (isSaved) {
                val imagePath =
                    File(context.cacheDir, "images")
                fileName?.let { newFile = File(imagePath, fileName) }
            }
        } catch (e: Exception) {
        }
        newFile?.let {
            val fileBody = ProgressRequestBody(
                it, "image", currentImageProgressLiveData,
                img.content_uri.toString()
            )
            val filePart =
                MultipartBody.Part.createFormData("photo", it.name, fileBody)

            val sourcePart =
                MultipartBody.Part.createFormData("source", "LC")
            uploadSingleImageManager.upload(
                filePart,
                currentImageProgressLiveData,
                img.content_uri.toString(),
                sourcePart,
                uploadImageInteractor,
                img,
                selectedImages
            )
        }
        cursor?.close()
    }


    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            context.contentResolver.openFileDescriptor(
                uri,
                "r"
            )
        val fileDescriptor: FileDescriptor? = parcelFileDescriptor?.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        return image
    }

    private fun saveImage(bitmap: Bitmap, fileName: String?): Boolean {
        try {
            cachePath = File(context.cacheDir, "images")
            cachePath?.mkdirs()
            val stream =
                FileOutputStream(cachePath.toString() + "/${fileName}") // only one image at a time cached
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
            return true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    @Throws(IOException::class)
    fun saveBitmap(
        context: Context, bitmap: Bitmap, format: Bitmap.CompressFormat,
        mimeType: String, displayName: String
    ): Uri {
        val relativeLocation = Environment.DIRECTORY_DCIM + File.separator + "Huddl";

        context.cacheDir
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, relativeLocation)
        }

        var uri: Uri? = null

        return runCatching {
            with(context.contentResolver) {
                insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)?.also {
                    uri = it // Keep uri reference so it can be removed on failure

                    openOutputStream(it)?.use { stream ->
                        if (!bitmap.compress(format, 95, stream))
                            throw IOException("Failed to save bitmap.")
                    } ?: throw IOException("Failed to open output stream.")

                } ?: throw IOException("Failed to create new MediaStore record.")
            }
        }.getOrElse {
            uri?.let { orphanUri ->
                // Don't leave an orphan entry in the MediaStore
                context.contentResolver.delete(orphanUri, null, null)
            }
            throw it
        }
    }

    private fun getNextElementIndexSmartly(
        selectedImages: ArrayList<UploadFormImage>,
        uploadedImage: UploadFormImage, canRetry: Boolean
    ): Int? {
        stopUploadOperationIfAllImagesUploaded(selectedImages)
        selectedImages.let { images ->
            val iterator = images.listIterator()
            while (iterator.hasNext()) {
                val currentElement = iterator.next()
                if (currentElement.image_path == uploadedImage.image_path) {
                    if (iterator.hasNext()) {
                        return iterator.nextIndex()
                    }
                }
            }
        }
        /** Retrying again if any creative left for uploading **/
        val allCreatives = selectedImages
        if (allCreatives.any { !it.is_uploaded } && canRetry) {
            return allCreatives.indexOf(allCreatives.first { !it.is_uploaded })
        } else {
            return null
        }
    }

    override fun stopUploadOperationIfAllImagesUploaded(selectedImages: ArrayList<UploadFormImage>) {
        selectedImages.let { images ->
            //DELETE LOCALLY SAVED IMAGES IN EXTERNAL DIR
            if(images.all { it.is_uploaded }) {
                deleteCache(context)
                StatusOfUpload.status = STATUS.SUCCESS
                StatusOfUpload.percentage = 100
                StatusOfUpload.content_uri = "bv43b5vb45vn"
                currentImageUploadProgressLiveData.postValue(StatusOfUpload)
                return@let
            }
        }
    }

    override fun startNextImageUpload(uploadedImage: UploadFormImage, canRetry: Boolean) {
        getNextElementIndexSmartly(selectedImages, uploadedImage, canRetry)?.let {
            currentUploadingIndex = it
            if (selectedImages.get(it).is_uploaded) {
                getNextElementIndexSmartly(
                    selectedImages,
                    selectedImages.get(it),
                    canRetry
                )?.let { newIndex ->
                    currentUploadingIndex = newIndex
                    startUploadQueue(selectedImages, newIndex)
                }
            } else {
                startUploadQueue(selectedImages, it)
            }
        }
    }

    fun deleteCache(context: Context) {
        try {
            val dir = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
        }
    }

    fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }

}