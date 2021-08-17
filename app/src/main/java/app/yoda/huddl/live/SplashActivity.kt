package app.yoda.huddl.live

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import app.yoda.huddl.live.AppUtils.SignInTokenManager
import app.yoda.huddl.live.Constants.HuddlConstants.SPLASH_TIME_OUT
import app.yoda.huddl.live.ui.auth.AuthActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


    @Inject
    lateinit var signInTokenManager: SignInTokenManager

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

    private fun redirect()
    {
        //Check if LoggedIn start HuddlLive else start AuthActivity

//        if(this::signInTokenManager.isInitialized && !signInTokenManager.isSignedin())
        startAuthActivity()
//        else
//            startHuddlLive()
    }

    private fun startAuthActivity()
    {
        startActivity(Intent(this, AuthActivity::class.java))
    }

    private fun startHuddlLive(){

    }
}