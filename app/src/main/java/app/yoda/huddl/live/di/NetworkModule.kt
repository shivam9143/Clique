package app.yoda.huddl.live.di

import app.yoda.huddl.live.AppUtils.HuddlInterceptor
import app.yoda.huddl.live.AppUtils.SignInTokenManager
import app.yoda.huddl.live.Constants.HuddlConstants
import app.yoda.huddl.live.network.main.MainApiHelper
import app.yoda.huddl.live.network.main.MainApi
import app.yoda.huddl.live.HuddlApplication
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(HuddlConstants.BASE_URL)
            .addConverterFactory(provideGsonConvertorFactory())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(huddlInterceptor: HuddlInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(huddlInterceptor)
            .connectTimeout(HuddlConstants.timeOut, TimeUnit.SECONDS)
            .readTimeout(HuddlConstants.timeOut, TimeUnit.SECONDS)
            .writeTimeout(HuddlConstants.timeOut, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesHuddlInterceptor(signInTokenManager: SignInTokenManager): HuddlInterceptor {
        return HuddlInterceptor(signInTokenManager)
    }

    @Provides
    @Singleton
    fun provideCache(application: HuddlApplication): Cache {
        val httpCacheDirectory = File(application.cacheDir, "responses")
        val cacheSize = 8 * 1024 * 1024 // 8 MiB
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideApiHelper(huddlApiService: MainApi): MainApiHelper =
        MainApiHelper(huddlApiService)


}