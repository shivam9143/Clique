package app.yoda.huddl.live.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import app.yoda.huddl.live.SessionManager
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: MainRepository, sessionManager: SessionManager) : ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        Log.e(TAG, TAG)
    }
}