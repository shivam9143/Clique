package yoda.huddl.live.di

import yoda.huddl.live.HuddlApplication

object Injector {
    lateinit var appComponent: AppComponent

    fun init(app : HuddlApplication) {
//        appComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()
//        appComponent.inject(app)
    }

}