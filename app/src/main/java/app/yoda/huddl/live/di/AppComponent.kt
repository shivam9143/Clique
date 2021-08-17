package app.yoda.huddl.live.di

import android.app.Application
import app.yoda.huddl.live.HuddlApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class,
        ViewModelFactoryModule::class, ActivityBuilderModule::class]
)

interface AppComponent : AndroidInjector<HuddlApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
