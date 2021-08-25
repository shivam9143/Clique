package yoda.huddl.live.ui.auth

import yoda.huddl.live.HuddleBaseRepository
import yoda.huddl.live.network.auth.AuthApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(val authApiHelper: AuthApiHelper) : HuddleBaseRepository() {

//    @Inject
//    lateinit var authApiHelper: AuthApiHelper

    suspend fun getIGAuthToken(code : String) =
        authApiHelper.getInstaAuthToken(code)


}