package yoda.huddl.live.network.Instagram

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import yoda.huddl.live.models.IgUserProfile

interface IgGraphApi {

    @GET("me/")
    suspend fun getIgUserProfile(
        @Query("fields") fields: String = "id,username",
        @Query("access_token") access_token: String,
    ) : IgUserProfile
}