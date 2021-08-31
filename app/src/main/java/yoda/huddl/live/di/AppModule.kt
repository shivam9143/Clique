package yoda.huddl.live.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import yoda.huddl.live.AppUtils.SignInTokenManager
import yoda.huddl.live.Offline.HuddlOfflineDataManager
import yoda.huddl.live.network.Instagram.IgGraphApi
import yoda.huddl.live.network.Instagram.InstagramAPi
import yoda.huddl.live.network.auth.AuthApi
import yoda.huddl.live.network.splash.SplashApi
import yoda.huddl.live.network.splash.SplashApiHelper
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            HuddlOfflineDataManager.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE

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
    @Singleton
    fun provideIGGraphApi(@Named("ig-graph") retrofit: Retrofit): IgGraphApi {
        return retrofit.create(IgGraphApi::class.java)
    }

//    @Provides
//    fun provideAuthApiService(authApi: AuthApi, instagramAPi: InstagramAPi): AuthApiHelper {
//        return AuthApiHelper(authApi, instagramAPi)
//    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}
