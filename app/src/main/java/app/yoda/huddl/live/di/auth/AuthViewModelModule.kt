package app.yoda.huddl.live.di.auth

import androidx.lifecycle.ViewModel
import app.yoda.huddl.live.di.ViewModelKey
import app.yoda.huddl.live.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun authViewModel(viewModel: AuthViewModel): ViewModel
}