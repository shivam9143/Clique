package yoda.huddl.live.AppUtils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import yoda.huddl.live.HuddlApplication
import yoda.huddl.live.Offline.HuddlOfflineDataManager
import yoda.huddl.live.Offline.HuddlOfflineDataManager.Companion.key_user_profile
import yoda.huddl.live.models.UserProfile

class HuddlUserProfileManager {
    companion object {
         val prefs: SharedPreferences = HuddlApplication.context.getSharedPreferences(
            HuddlOfflineDataManager.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE
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