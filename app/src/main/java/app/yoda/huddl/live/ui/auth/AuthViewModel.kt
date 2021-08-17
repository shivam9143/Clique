package app.yoda.huddl.live.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.yoda.huddl.live.SessionManager
import app.yoda.huddl.live.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(val sessionManager: SessionManager, val authApi: AuthApi,
                                        val authRepository: AuthRepository): ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        Log.e(TAG, TAG)
    }

    fun getAuthState() : LiveData<AuthState> = sessionManager.getAuthUser()

    fun authenticateUser()
    {
        //authenticate user with auth token and then save token in SessionManager

    }

}