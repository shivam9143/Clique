package yoda.huddl.live.network.auth

import yoda.huddl.live.network.Instagram.IgGraphApi
import yoda.huddl.live.network.Instagram.InstagramAPi
import javax.inject.Inject

class AuthApiHelper @Inject constructor(val authApi: AuthApi, val instagramAPi: InstagramAPi, val igGraphApi: IgGraphApi) {

//    @Inject
//    lateinit var authApi: AuthApi

    suspend fun getInstaAuthToken(code: String) =
        instagramAPi.getAuthIgAuthToken(code = code)

    suspend fun getIgUserProfile(token: String) =
        igGraphApi.getIgUserProfile(access_token = token)

}