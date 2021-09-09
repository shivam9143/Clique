package yoda.huddl.live.network.auth

import app.yoda.huddl.huddlutils.CreateUserProfile
import yoda.huddl.live.models.TokenReqBody
import yoda.huddl.live.network.Instagram.IgGraphApi
import yoda.huddl.live.network.Instagram.InstagramAPi
import javax.inject.Inject

class AuthApiHelper @Inject constructor(
    val authApi: AuthApi,
    val instagramAPi: InstagramAPi,
    val igGraphApi: IgGraphApi
) {

    suspend fun getInstaAuthToken(code: String) =
        instagramAPi.getAuthIgAuthToken(code = code)

    suspend fun getIgUserProfile(token: String) =
        igGraphApi.getIgUserProfile(access_token = token)

    suspend fun getIgUserMedia(token: String) =
        igGraphApi.getIgUserMedia(access_token = token)

    suspend fun authenticateUser(token: String) =
        authApi.authenicateUser(TokenReqBody(token))

    suspend fun setUserProfile(userProfile: CreateUserProfile) =
        authApi.setUserProfile(userProfile)

    suspend fun getUserProfile() =
        authApi.getUserProfile()

}