package yoda.huddl.live.di.auth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import yoda.huddl.live.di.ActivityComponent
import yoda.huddl.live.network.Instagram.InstagramAPi
import yoda.huddl.live.network.auth.AuthApi
import yoda.huddl.live.network.auth.AuthApiHelper
import yoda.huddl.live.ui.auth.AuthActivity
import yoda.huddl.live.ui.auth.AuthRepository


//@Module
//@InstallIn(SingletonComponent::class)
object AuthModule {

//    @Provides
//    fun provideAuthApiService(authApi: AuthApi, instagramAPi: InstagramAPi): AuthApiHelper {
//        return AuthApiHelper(authApi, instagramAPi )
//    }

//    @Provides
//    fun provideAuthApi(retrofit: Retrofit): AuthApi {
//        return retrofit.create(AuthApi::class.java)
//    }

//    @Provides
//    fun providesAuthRepository(authApiHelper: AuthApiHelper) : AuthRepository {
//        return AuthRepository()
//    }

}