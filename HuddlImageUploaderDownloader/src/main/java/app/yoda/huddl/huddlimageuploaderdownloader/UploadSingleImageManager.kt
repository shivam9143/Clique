package app.yoda.huddl.huddlimageuploaderdownloader

import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.yoda.huddl.huddlimageuploaderdownloader.models.UploadFormImage
import app.yoda.huddl.huddlutils.Coroutines
import app.yoda.huddl.huddlutils.ResultWrapper
import kotlinx.coroutines.Job
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadSingleImageManager @Inject constructor(val repository: ImageUploadDownloadRepository) {

    var uploadJob: Job? = null

    fun upload(
        image: MultipartBody.Part,
        currentImageUploadProgressLiveData: MutableLiveData<StatusOfUpload>,
        contentUri: String,
        source: MultipartBody.Part,
        uploadCreativeInteractor: UploadImageInteractor,
        img: UploadFormImage,
        selectedImages : ArrayList<UploadFormImage>
    ) {
        uploadJob = Coroutines.ioReturningJob {
            var res = repository.uploadFile(photo = image, source = source)
            Log.e("res", res.toString())
            when (res) {
                is ResultWrapper.Success -> {
                    try {
                        Coroutines.main {
                            StatusOfUpload.status = STATUS.SUCCESS
                            StatusOfUpload.percentage = 100
                            StatusOfUpload.content_uri = contentUri
                            currentImageUploadProgressLiveData.value = StatusOfUpload
                            img.is_uploaded = true
                            img.is_failed_to_upload = false
//                    img.uploaded_id = response.data.id
                            uploadCreativeInteractor.startNextImageUpload(img, true)
                        }
                    }
                    catch (e : Exception){}
                }
                is ResultWrapper.NetworkError -> {

                }

                is ResultWrapper.GenericError -> {

                }
            }
        }
    }
}