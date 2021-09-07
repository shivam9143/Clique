package yoda.huddl.live.AppUtils

import android.content.Context
import android.content.SharedPreferences
import app.yoda.huddl.huddlutils.HuddlConstants
import app.yoda.huddl.huddlutils.HuddlConstants.key_user_profile
import com.google.gson.Gson
import yoda.huddl.live.HuddlApplication
import yoda.huddl.live.models.UserProfile

class HuddlUserProfileManager {
    companion object {
         val prefs: SharedPreferences = HuddlApplication.context.getSharedPreferences(
            HuddlConstants.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE
        )

        var userProfile: UserProfile?
            set(userProfile) {
                prefs.edit().putString(key_user_profile, Gson().toJson(userProfile)).apply()
            }
            get() {
                return Gson().fromJson(
                    prefs.getString(
                        key_user_profile, null
                    ), UserProfile::class.java
                )
            }
    }
}