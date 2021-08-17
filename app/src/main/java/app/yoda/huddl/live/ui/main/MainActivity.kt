package app.yoda.huddl.live.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.yoda.huddl.live.HuddleBaseActivity
import app.yoda.huddl.live.R
import app.yoda.huddl.live.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : HuddleBaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}