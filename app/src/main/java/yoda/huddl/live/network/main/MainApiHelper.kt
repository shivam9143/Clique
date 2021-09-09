package yoda.huddl.live.network.main

import yoda.huddl.live.network.Instagram.IgGraphApi
import javax.inject.Inject

class MainApiHelper @Inject constructor(val mainApi: MainApi, val igGraphApi: IgGraphApi) {


    suspend fun getIgUserMedia(token: String) =
        igGraphApi.getIgUserMedia(access_token = token)

    suspend fun getIgUserMediaDetails(media_id : String,token: String) =
        igGraphApi.getIgUserMediaDetails(media_id= media_id, access_token = token)


}