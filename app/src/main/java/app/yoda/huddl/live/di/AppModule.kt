package app.yoda.huddl.live.di

import android.content.Context
import android.content.SharedPreferences
import app.yoda.huddl.live.AppUtils.SignInTokenManager
import app.yoda.huddl.live.Constants.HuddlConstants.SHARED_PREFS_FILE_NAME
import app.yoda.huddl.live.HuddlApplication
import app.yoda.huddl.live.di.auth.AuthScope
import app.yoda.huddl.live.network.auth.AuthApi
import app.yoda.huddl.live.network.auth.AuthApiHelper
import app.yoda.huddl.live.network.splash.SplashApi
import app.yoda.huddl.live.network.splash.SplashApiHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(application : HuddlApplication): SharedPreferences {
        return application.getSharedPreferences(
            SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideTokenManger(preferences: SharedPreferences): SignInTokenManager {
        return SignInTokenManager(preferences)
    }


    @Provides
    @Singleton
    fun provideSplashApiService(splashApi: SplashApi): SplashApiHelper {
        return SplashApiHelper(splashApi)
    }

    @Provides
    @Singleton
    fun provideSplashApi(retrofit: Retrofit): SplashApi {
        return retrofit.create(SplashApi::class.java)
    }


}
