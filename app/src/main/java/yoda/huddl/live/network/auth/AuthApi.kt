package yoda.huddl.live.network.auth


import androidx.lifecycle.LiveData
import retrofit2.http.POST
import retrofit2.http.Path
import yoda.huddl.live.models.IGAuthTokenRes


interface AuthApi {

//    @GET("users/{id}")
//    fun getUser(
//        @Path("id") id: Int
//    ): Flowable<com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User?>?

    @POST
    suspend fun getAuthIgAuthToken(
        @Path("client_id") client_id: String,
        @Path("client_secret") client_secret: String,
        @Path("code") code: String,
        @Path("grant_type") grant_type: String = "authorization_code",
        @Path("redirect_uri") redirect_uri: String = "https://httpstat.us/200"
    ) : LiveData<IGAuthTokenRes>

}