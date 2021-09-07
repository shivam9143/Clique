package yoda.huddl.live.network.main

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import yoda.huddl.live.models.TokenReqBody
import yoda.huddl.live.models.UserAuthToken
import javax.inject.Inject

interface MainApi {

//    @POST("auth/phone/")
//    suspend fun authenicateUser(
//        @Body token: TokenReqBody
//    ) : UserAuthToken

    @Multipart
    @POST("creator/photo/")
    suspend fun uploadProfileImages(
        @Part photo: MultipartBody.Part,
        @Part source: MultipartBody.Part
    ): JsonObject



}