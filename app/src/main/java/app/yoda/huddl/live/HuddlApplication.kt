package app.yoda.huddl.live

import android.content.Context
import app.yoda.huddl.live.di.AppModule
import app.yoda.huddl.live.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class HuddlApplication : DaggerApplication() {

    companion object {
        lateinit var context: HuddlApplication

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initializeFresco()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    private fun initializeFresco() {
        Fresco.initialize(this)
    }
}