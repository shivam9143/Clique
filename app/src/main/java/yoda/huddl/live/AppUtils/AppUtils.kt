package yoda.huddl.live.AppUtils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import yoda.huddl.live.ui.auth.AuthActivity
import yoda.huddl.live.ui.main.MainActivity


object AppUtils {

    fun validateName(name: String): Boolean {
        if (name != null && name.length > 2)
            return true
        return false
    }

    fun validateBio(bio: String): Boolean {
        if (bio != null && bio.length > 40)
            return true
        return false
    }

    fun validateEmail(email: String): Boolean {
        return email != null && email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(
            email
        ).matches();
    }

    fun validateMobile(countryCode: String, moNo: String): Boolean {
        return moNo != null && moNo.isNotEmpty() && moNo.length == 10
    }




}