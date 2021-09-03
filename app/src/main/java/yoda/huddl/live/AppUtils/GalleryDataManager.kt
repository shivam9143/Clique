package yoda.huddl.live.AppUtils

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import yoda.huddl.live.models.GalleryItem


class GalleryDataManager {
    companion object {
        private val TAG: String? = "GALLERY_FRAG"

        fun fetchGalleryImages(context: Activity): MutableList<GalleryItem> {
            val filePaths = mutableListOf<GalleryItem>()
            val resolver: ContentResolver = context.contentResolver
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.SIZE
            )
            val selection = "${MediaStore.Images.Media.SIZE} <= ?"
            val selectionArgs = arrayOf(
                (25*1024*1024).toString()
            )
            val cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                MediaStore.MediaColumns.DATE_ADDED + " DESC")
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val data = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                    val contentUrl = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                    val imageSizeColumn = cursor.getColumnIndex(MediaStore.Images.Media.SIZE)
                    val imageSize = cursor.getInt(imageSizeColumn)
                    Log.d("IMG_SIZE", imageSize.toString())

                    val imageUri = Uri.withAppendedPath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "" + cursor.getInt(contentUrl))
                    val path_actual = cursor.getString(data)
                    Log.d("IMG_URI", path_actual)
                    Log.d("IMG_URI_content", imageUri.toString())
                    path_actual?.let { filePaths.add(GalleryItem(it,false, imageUri)) }
                    if (filePaths.size >= 300)
                        break
                }
                cursor.close()
            }
            Log.d(TAG, "fetchGalleryImages: "+filePaths.size)
            return filePaths
        }
    }


}