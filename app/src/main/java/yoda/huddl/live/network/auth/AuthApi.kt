package yoda.huddl.live.network.auth


import androidx.lifecycle.LiveData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import yoda.huddl.live.models.IGAuthTokenRes
import yoda.huddl.live.models.IgUserProfile
import yoda.huddl.live.models.TokenReqBody
import yoda.huddl.live.models.UserAuthToken


interface AuthApi {

//    @GET("users/{id}")
//    fun getUser(
//        @Path("id") id: Int
//    ): Flowable<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User?>?


    @POST("auth/phone/")
    suspend fun authenicateUser(
        @Body token: TokenReqBody
    ) : UserAuthToken

}