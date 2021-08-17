package app.yoda.huddl.live.di

import androidx.lifecycle.ViewModelProvider
import app.yoda.huddl.live.ViewModelProviderFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?
}