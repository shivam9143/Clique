package yoda.huddl.live

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import yoda.huddl.live.ui.auth.*
import yoda.huddl.live.ui.main.MainActivity
import javax.inject.Inject

open class HuddleBaseActivity : AppCompatActivity() {


    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    //    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
    private val TAG = "HuddleBaseActivity"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeAuthObservers()
    }

    private fun subscribeAuthObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            when (it) {
                is AuthStateLoading -> {

                }
                is AuthStatePhoneAuthenticated -> {
                }
                is AuthStateError -> {

                }
                is AuthStateNotAuthenticated -> {
                    navigateToLoginScreen()
                }
                is AuthStateIdle -> {

                }
                is AuthStateInstagramAuthenticated -> {
                    navigateToMainHuddlApp()
                }
            }
        })
    }

    private fun navigateToLoginScreen() {
        Log.e(TAG, "from base to login")
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun navigateToMainHuddlApp() {
        Log.e(TAG, "from base to Main APp")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}