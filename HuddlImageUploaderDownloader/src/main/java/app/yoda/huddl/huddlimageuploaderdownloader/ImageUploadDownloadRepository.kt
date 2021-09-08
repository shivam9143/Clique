package app.yoda.huddl.huddlimageuploaderdownloader

import app.yoda.huddl.huddlutils.HuddleBaseRepository
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageUploadDownloadRepository @Inject constructor(val uploadApiHelper: UploadApiHelper) :  HuddleBaseRepository() {

    suspend fun uploadFile(photo : MultipartBody.Part, source : MultipartBody.Part) =
        huddleSafeApiCall{
            uploadApiHelper.uploadFile(photo = photo, source = source)
        }


}