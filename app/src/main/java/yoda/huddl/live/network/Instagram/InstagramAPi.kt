package yoda.huddl.live.network.Instagram

import androidx.lifecycle.LiveData
import retrofit2.http.POST
import retrofit2.http.Path
import yoda.huddl.live.Constants.HuddlConstants
import yoda.huddl.live.models.IGAuthTokenRes

interface InstagramAPi {
    @POST
    suspend fun getAuthIgAuthToken(
        @Path("client_id") client_id: String = HuddlConstants.IG_CLIENT_ID,
        @Path("client_secret") client_secret: String = HuddlConstants.IG_APP_SEC_KEY,
        @Path("code") code: String,
        @Path("grant_type") grant_type: String = "authorization_code",
        @Path("redirect_uri") redirect_uri: String = "https://httpstat.us/200"
    ) : IGAuthTokenRes

}