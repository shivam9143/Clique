package app.yoda.huddl.live

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import app.yoda.huddl.live.ui.auth.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class HuddleBaseActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeAuthObservers()
    }

    private fun subscribeAuthObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            when (it) {
                is AuthStateLoading -> {

                }
                is AuthStateAuthenticated -> {

                }
                is AuthStateError -> {

                }
                is AuthStateNotAuthenticated -> {
                    navigateToLoginScreen()
                }
                is AuthStateIdle -> {

                }
            }
        })
    }

    private fun navigateToLoginScreen()
    {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}