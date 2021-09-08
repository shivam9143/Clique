package app.yoda.huddl.huddlutils

import android.content.Context
import android.content.SharedPreferences
import app.yoda.huddl.huddlutils.HuddlConstants.SHARED_PREF_FILE_NAME
import app.yoda.huddl.huddlutils.HuddlConstants.key_auth_token
import app.yoda.huddl.huddlutils.HuddlConstants.key_user_insta_authenticated
import app.yoda.huddl.huddlutils.HuddlConstants.key_user_mob_no
import app.yoda.huddl.huddlutils.HuddlConstants.key_user_phone_authenticated
import app.yoda.huddl.huddlutils.HuddlConstants.key_user_profile
import com.google.gson.Gson

class HuddlOfflineDataManager constructor(var context: Context) {

//    companion object {
//        val SHARED_PREF_FILE_NAME = "yoda.huddl.live.sp"
//        val key_auth_token = "auth_token"
//        val key_user_profile = "user_profile"
//        val key_user_mob_no = "user_mob_no"
//        val key_user_phone_authenticated = "key_user_phone_authenticated"
//        val key_user_insta_authenticated = "key_user_insta_authenticated"
//


//        fun getUserAuthToken(): String? {
//            val prefs: SharedPreferences =
//                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
//            return prefs.getString(key_auth_token, "")
//        }
//
//        fun setUserProfile(userProfile: UserProfile) {
//            val prefs: SharedPreferences =
//                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
//            prefs.edit().putString(key_user_profile, Gson().toJson(userProfile)).apply()
//        }

        fun getUserProfile(): UserProfile? {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            return Gson().fromJson(
                prefs.getString(
                    key_user_profile, null
                ), UserProfile::class.java
            )
        }

        fun setUserAuthToken(authToken: String?) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(key_auth_token, authToken).apply()
        }

        fun setUserMobNo(mobNo: String?) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(key_user_mob_no, mobNo).apply()
        }

        fun setUserPhoneAuthenticated(auth: Boolean) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(key_user_phone_authenticated, auth).apply()
        }
//        fun setUserAuthState(authState: AuthState) {
//            val prefs: SharedPreferences =
//                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
//            prefs.edit().put(key_user_phone_authenticated, auth).apply()
//        }

        fun setUserInstaAuthenticated(auth: Boolean) {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(key_user_insta_authenticated, auth).apply()
        }

        fun getUserPhoneAuthenticated() {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.getBoolean(key_user_phone_authenticated, false)
        }

        fun getUserInstaAuthenticated() {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            prefs.getBoolean(key_user_insta_authenticated, false)
        }

        fun getUserMobNo(): String? {
            val prefs: SharedPreferences =
                context.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
            return prefs.getString(key_user_mob_no, "")
        }


//    }
}