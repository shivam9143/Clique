package yoda.huddl.live.AppUtils

import android.content.SharedPreferences
import yoda.huddl.live.Offline.HuddlOfflineDataManager.Companion.key_auth_token

class SignInTokenManager(var preferences: SharedPreferences) {

    var token: String?
        get() = preferences.getString(key_auth_token, null)
        set(token) = preferences.edit().putString(key_auth_token, token).apply()

    fun deleteToken() = preferences.edit().remove(key_auth_token).apply()

    fun isSignedin(): Boolean {
        return token != null
    }
}