package yoda.huddl.live.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yoda.huddl.live.ViewModelFactory
import yoda.huddl.live.di.ViewModelKey
import yoda.huddl.live.ui.auth.AuthViewModel

@Module
abstract class AuthViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun authViewModel(viewModel: AuthViewModel): ViewModel
}