package yoda.huddl.live.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import app.yoda.huddl.huddlutils.SignInTokenManager
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.R
import yoda.huddl.live.Constants.HuddlConstants.SPLASH_TIME_OUT
import yoda.huddl.live.ui.auth.AuthActivity
import yoda.huddl.live.ui.main.MainActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var signInTokenManager: SignInTokenManager

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleHuddleActivity()
    }

    private fun scheduleHuddleActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            redirect()
            finish()
        }, SPLASH_TIME_OUT)
    }

    private fun redirect() {
        //Check if LoggedIn start HuddlLive else start AuthActivity
//        if(this::signInTokenManager.isInitialized && !signInTokenManager.isSignedin())
        if(!signInTokenManager.isSignedin())
        startAuthActivity()
        else
            startHuddlLive()
    }

    private fun startAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
    }

    private fun startHuddlLive() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}