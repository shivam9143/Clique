package yoda.huddl.live.network.Instagram

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import yoda.huddl.live.models.IgUserMedia
import yoda.huddl.live.models.IgUserProfile

interface IgGraphApi {

    @GET("me/")
    suspend fun getIgUserProfile(
        @Query("fields") fields: String = "id,username,media_count",
        @Query("access_token") access_token: String,
    ) : IgUserProfile


    @GET("me/media/")
    suspend fun getIgUserMedia(
        @Query("fields") fields: String = "id,username",
        @Query("access_token") access_token: String,
    ) : IgUserMedia

    @GET("{media_id}/")
    suspend fun getIgUserMediaDetails(
        @Path("media_id") media_id : String,
        @Query("fields") fields: String = "id,media_type,media_url,permalink,username",
        @Query("access_token") access_token: String,
    ) : IgUserMedia

}