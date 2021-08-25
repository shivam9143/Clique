package yoda.huddl.live.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import yoda.huddl.live.AppUtils.SignInTokenManager
import yoda.huddl.live.Constants.HuddlConstants.SHARED_PREFS_FILE_NAME
import yoda.huddl.live.HuddlApplication
import yoda.huddl.live.network.Instagram.InstagramAPi
import yoda.huddl.live.network.auth.AuthApi
import yoda.huddl.live.network.auth.AuthApiHelper
import yoda.huddl.live.network.splash.SplashApi
import yoda.huddl.live.network.splash.SplashApiHelper
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule(application: HuddlApplication) {

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

    @Provides
    @Singleton
    fun provideIGApi(@Named("ig") retrofit: Retrofit): InstagramAPi {
        return retrofit.create(InstagramAPi::class.java)
    }

    @Provides
    fun provideAuthApiService(authApi: AuthApi, instagramAPi: InstagramAPi): AuthApiHelper {
        return AuthApiHelper(authApi, instagramAPi)
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


}
