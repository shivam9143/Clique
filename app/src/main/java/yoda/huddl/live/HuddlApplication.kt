package yoda.huddl.live

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import app.yoda.huddl.huddlutils.HuddlOfflineDataManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HuddlApplication : Application() {

    companion object {
        lateinit var context: HuddlApplication
        lateinit var huddlOfflineDataManager: HuddlOfflineDataManager
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
        huddlOfflineDataManager = HuddlOfflineDataManager(this)
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }



    private fun initializeFresco() {
        Fresco.initialize(this)
    }
}