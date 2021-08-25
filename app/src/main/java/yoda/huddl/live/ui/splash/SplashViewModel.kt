package yoda.huddl.live.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(repository: SplashRepository) : ViewModel() {

    private val TAG = "SplashViewModel"

    init {
        Log.e(TAG, TAG)
    }
}