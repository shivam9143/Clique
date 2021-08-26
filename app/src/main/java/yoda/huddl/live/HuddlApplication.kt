package yoda.huddl.live

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HuddlApplication : Application() {

    companion object {
        lateinit var context: HuddlApplication
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initializeFresco()
        initFirebase()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }



    private fun initializeFresco() {
        Fresco.initialize(this)
    }
}