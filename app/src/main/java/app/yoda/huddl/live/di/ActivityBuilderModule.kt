package app.yoda.huddl.live.di

import app.yoda.huddl.live.di.auth.AuthModule
import app.yoda.huddl.live.di.auth.AuthViewModelModule
import app.yoda.huddl.live.di.main.MainModule
import app.yoda.huddl.live.di.main.MainViewModelModule
import app.yoda.huddl.live.ui.auth.AuthActivity
import app.yoda.huddl.live.ui.main.MainActivity
import dagger.Module

import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    //Only Auth activity will be able to use this AuthViewModelModule(Sub Component)
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity?

    @ContributesAndroidInjector(modules = [MainViewModelModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity?
}
