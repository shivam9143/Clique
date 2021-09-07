package app.yoda.huddl.huddlutils

import android.content.SharedPreferences
import app.yoda.huddl.huddlutils.HuddlConstants.key_auth_token

class SignInTokenManager(var preferences: SharedPreferences) {

    var token: String?
        get() = preferences.getString(key_auth_token, null)
        set(token) = preferences.edit().putString(key_auth_token, token).apply()

    fun deleteToken() = preferences.edit().remove(key_auth_token).apply()

    fun isSignedin(): Boolean {
        return token != null
    }
}