package app.yoda.huddl.huddlimageuploaderdownloader.di

import app.yoda.huddl.huddlimageuploaderdownloader.UploadApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object FileUploadModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): UploadApi {
        return retrofit.create(UploadApi::class.java)
    }

}