package app.yoda.huddl.live.di.auth

import app.yoda.huddl.live.network.auth.AuthApi
import app.yoda.huddl.live.network.auth.AuthApiHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AuthModule {

    @Provides
    fun provideAuthApiService(authApi: AuthApi): AuthApiHelper {
        return AuthApiHelper(authApi)
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi? {
        return retrofit.create(AuthApi::class.java)
    }

}