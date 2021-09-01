package yoda.huddl.live.ui.auth

import yoda.huddl.live.HuddleBaseRepository
import yoda.huddl.live.models.CreateUserProfile
import yoda.huddl.live.network.auth.AuthApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(val authApiHelper: AuthApiHelper) : HuddleBaseRepository() {

//    @Inject
//    lateinit var authApiHelper: AuthApiHelper

    suspend fun getIGAuthToken(code : String) =
        authApiHelper.getInstaAuthToken(code)

    suspend fun getIgUserProfile(token : String) =
        authApiHelper.getIgUserProfile(token)

    suspend fun authenticateUser(token: String) =
        authApiHelper.authenticateUser(token)

    suspend fun getUserProfile() =
        authApiHelper.getUserProfile()

    suspend fun setUserProfile(userProfile : CreateUserProfile,) =
        authApiHelper.setUserProfile(userProfile)


}