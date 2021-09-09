package yoda.huddl.live.ui.main

import app.yoda.huddl.huddlutils.HuddleBaseRepository
import yoda.huddl.live.network.main.MainApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(val mainApiHelper: MainApiHelper): HuddleBaseRepository() {

    suspend fun getIgUserMedia(token: String) =
        mainApiHelper.getIgUserMedia(token = token)

    suspend fun getIgUserMediaDetails(media_id : String,token: String) =
        mainApiHelper.getIgUserMediaDetails(media_id= media_id, token = token)

}