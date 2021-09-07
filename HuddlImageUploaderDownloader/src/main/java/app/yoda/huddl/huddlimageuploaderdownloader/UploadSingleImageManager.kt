package app.yoda.huddl.huddlimageuploaderdownloader

import androidx.lifecycle.MutableLiveData
import app.yoda.huddl.huddlutils.Coroutines
import kotlinx.coroutines.Job
import okhttp3.MultipartBody
import retrofit2.http.Multipart

class UploadSingleImageManager() {

    var uploadJob: Job? = null

    fun upload(image: MultipartBody.Part,
    currentImageUploadProgressLiveData: MutableLiveData<StatusOfUpload>,
    contentUri : String) {
        uploadJob = Coroutines.ioReturningJob {

        }
    }
}