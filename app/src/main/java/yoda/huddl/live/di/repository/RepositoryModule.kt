package yoda.huddl.live.di.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import yoda.huddl.live.HuddleBaseRepository
import yoda.huddl.live.network.auth.AuthApiHelper
import yoda.huddl.live.ui.auth.AuthRepository

//@Module
//@InstallIn(ViewModelComponent::class)
//object RepositoryModule {
//    @Provides
//    @ViewModelScoped
//    fun provideAuthRepository(
//        authApiHelper: AuthApiHelper
//        ): AuthRepository {
//        return AuthRepository(authApiHelper)
//    }
//}