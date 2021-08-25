package yoda.huddl.live.di


import dagger.Module

import dagger.android.ContributesAndroidInjector
import yoda.huddl.live.di.auth.AuthModule
import yoda.huddl.live.di.auth.AuthScope
import yoda.huddl.live.di.auth.AuthViewModelModule
import yoda.huddl.live.di.main.MainModule
import yoda.huddl.live.di.main.MainScope
import yoda.huddl.live.di.main.MainViewModelModule
import yoda.huddl.live.ui.auth.AuthActivity
import yoda.huddl.live.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {
    //Only Auth activity will be able to use this AuthViewModelModule(Sub Component)
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity?

    @MainScope
    @ContributesAndroidInjector(modules = [MainViewModelModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity?
}
