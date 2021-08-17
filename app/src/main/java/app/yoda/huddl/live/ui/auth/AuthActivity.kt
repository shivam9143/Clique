package app.yoda.huddl.live.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.yoda.huddl.live.HuddleBaseActivity
import app.yoda.huddl.live.R
import app.yoda.huddl.live.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : HuddleBaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initViewModel()
    }

    private fun initViewModel()
    {
        authViewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
    }

}