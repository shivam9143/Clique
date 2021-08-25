package yoda.huddl.live.AppUtils

import android.content.SharedPreferences
import yoda.huddl.live.Offline.HuddlOfflineDataManager.Companion.AUTH_TOKEN

class SignInTokenManager(var preferences: SharedPreferences) {

    var token: String?
        get() = preferences.getString(AUTH_TOKEN, null)
        set(token) = preferences.edit().putString(AUTH_TOKEN, token).apply()

    fun deleteToken() = preferences.edit().remove(AUTH_TOKEN).apply()

    fun isSignedin(): Boolean {
        return token != null
    }
}