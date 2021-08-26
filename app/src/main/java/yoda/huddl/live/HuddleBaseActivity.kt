package yoda.huddl.live

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import yoda.huddl.live.ui.auth.*
import javax.inject.Inject

open class HuddleBaseActivity : AppCompatActivity() {


    @Inject
    lateinit var sessionManager: SessionManager

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

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
            }
        })
    }

    private fun navigateToLoginScreen()
    {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}