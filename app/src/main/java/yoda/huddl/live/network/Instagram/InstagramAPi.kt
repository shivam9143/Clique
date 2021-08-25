package yoda.huddl.live.network.Instagram

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import yoda.huddl.live.Constants.HuddlConstants
import yoda.huddl.live.models.IGAuthTokenRes

interface InstagramAPi {

    @FormUrlEncoded
    @POST("oauth/access_token/")
    suspend fun getAuthIgAuthToken(
        @Field("client_id") client_id: String = HuddlConstants.IG_CLIENT_ID,
        @Field("client_secret") client_secret: String = HuddlConstants.IG_APP_SEC_KEY,
        @Field("code") code: String,
        @Field("grant_type") grant_type: String = "authorization_code",
        @Field("redirect_uri") redirect_uri: String = "https://httpstat.us/200"
    ) : IGAuthTokenRes

}