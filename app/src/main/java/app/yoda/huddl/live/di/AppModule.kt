package app.yoda.huddl.live.di

import android.content.Context
import android.content.SharedPreferences
import app.yoda.huddl.live.AppUtils.SignInTokenManager
import app.yoda.huddl.live.Constants.HuddlConstants.SHARED_PREFS_FILE_NAME
import app.yoda.huddl.live.HuddlApplication
import dagger.Module
import dagger.Provides
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

}
