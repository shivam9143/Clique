package yoda.huddl.live.network.auth


import androidx.lifecycle.LiveData
import app.yoda.huddl.huddlutils.BaseResponse
import app.yoda.huddl.huddlutils.CreateUserProfile
import app.yoda.huddl.huddlutils.UserProfile
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
    ) : BaseResponse<UserAuthToken>

    @PATCH("creator/profile/me/")
    suspend fun setUserProfile(
        @Body userProfile : CreateUserProfile
    ) : BaseResponse<UserProfile>

    @GET("creator/profile/me/")
    suspend fun getUserProfile() : BaseResponse<UserProfile>


}