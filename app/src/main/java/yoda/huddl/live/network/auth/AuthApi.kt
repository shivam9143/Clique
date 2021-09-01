package yoda.huddl.live.network.auth


import androidx.lifecycle.LiveData
import retrofit2.http.*
import yoda.huddl.live.models.*


interface AuthApi {

//    @GET("users/{id}")
//    fun getUser(
//        @Path("id") id: Int
//    ): Flowable<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User?>?

    @POST("auth/phone/")
    suspend fun authenicateUser(
        @Body token: TokenReqBody
    ) : UserAuthToken

    @PATCH("creator/profile/me/")
    suspend fun setUserProfile(
        @Body userProfile : CreateUserProfile
    ) : UserProfile

    @GET("creator/profile/me/")
    suspend fun getUserProfile() : UserProfile


}