package app.yoda.huddl.live.di.main

import app.yoda.huddl.live.network.main.MainApi
import app.yoda.huddl.live.network.main.MainApiHelper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainApiService(mainApi: MainApi): MainApiHelper {
        return MainApiHelper(mainApi)
    }

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi? {
        return retrofit.create(MainApi::class.java)
    }

}