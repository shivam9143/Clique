package app.yoda.huddl.huddlimageuploaderdownloader

import app.yoda.huddl.huddlutils.BaseResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApi {

    @Multipart
    @POST("creator/photo/")
    suspend fun uploadProfileImages(
        @Part photo: MultipartBody.Part): BaseResponse<JsonObject>

}