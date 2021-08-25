package yoda.huddl.live.di.main


import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import yoda.huddl.live.network.main.MainApi
import yoda.huddl.live.network.main.MainApiHelper
import yoda.huddl.live.ui.main.MainRepository

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApiService(mainApi: MainApi): MainApiHelper {
        return MainApiHelper(mainApi)
    }

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    fun providesMainRepository() : MainRepository {
        return MainRepository()
    }

}