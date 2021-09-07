package app.yoda.huddl.huddlutils


import android.content.Context
import android.content.SharedPreferences
import app.yoda.huddl.huddlutils.HuddlConstants.SHARED_PREF_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HuddlConstants.BASE_URL)
            .client(client)
            .addConverterFactory(provideGsonConvertorFactory())
            .build()
    }

    @Named("ig")
    @Singleton
    @Provides
    fun provideIGRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HuddlConstants.IG_BASE_URL)
            .addConverterFactory(provideGsonConvertorFactory())
            .client(client)
            .build()
    }

    @Named("ig-graph")
    @Singleton
    @Provides
    fun provideIGGraphRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HuddlConstants.IG_GRAPH_BASE_URL)
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
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
    fun provideTokenManger(preferences: SharedPreferences): SignInTokenManager {
        return SignInTokenManager(preferences)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE
        )
    }



//    @Provides
//    @Singleton
//    fun provideCache(application: HuddlApplication): Cache {
//        val httpCacheDirectory = File(application.cacheDir, "responses")
//        val cacheSize = 8 * 1024 * 1024 // 8 MiB
//        return Cache(httpCacheDirectory, cacheSize.toLong())
//    }

//    @Provides
//    @Singleton
//    fun provideApiHelper(huddlApiService: MainApi): MainApiHelper =
//        MainApiHelper(huddlApiService)


}