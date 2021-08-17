package app.yoda.huddl.live.di.main

import androidx.lifecycle.ViewModel
import app.yoda.huddl.live.di.ViewModelKey
import app.yoda.huddl.live.ui.auth.AuthViewModel
import app.yoda.huddl.live.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}