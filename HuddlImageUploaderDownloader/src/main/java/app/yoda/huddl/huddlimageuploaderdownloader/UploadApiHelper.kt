package app.yoda.huddl.huddlimageuploaderdownloader

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UploadApiHelper @Inject constructor(val uploadApi: UploadApi) {

    suspend fun uploadFile(photo : MultipartBody.Part, source : MultipartBody.Part) =
        uploadApi.uploadProfileImages(photo = photo, source)

}