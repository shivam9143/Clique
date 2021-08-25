package yoda.huddl.live.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import yoda.huddl.live.HuddlApplication
import yoda.huddl.live.di.auth.AuthModule
import yoda.huddl.live.ui.auth.AuthActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class]
)
interface AppComponent  {

    fun inject(appComponent: HuddlApplication)
    fun inject(authActivity: AuthActivity)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule) :  Builder
        fun build(): AppComponent
    }
}

//@Singleton
//@Component(modules = [ApplicationModule::class])
//interface ApplicationComponent {
//    fun inject(app: YodaApplication)
//    fun inject(yodaLandingActvity: YodaLandingActvity)
//    fun inject(feedFragment: YodaFeedFragment)
//    fun inject(notificationFragment: NotificationFragment)
//
//    @Component.Builder
//    interface Builder {
//        fun applicationModule(appModule: ApplicationModule): Builder
//        fun build(): ApplicationComponent
//    }
//}

//
//@Singleton
//@Component(modules = [AppModule::class])
//interface ApplicationComponent {
//
//    fun inject(app :BaseApplication)
//    fun inject(viewModel : GridViewViewModel)
//
//    @Component.Builder
//    interface Builder {
//        fun appModule(appModule : AppModule) : Builder
//        fun build(): ApplicationComponent
//    }
//}
