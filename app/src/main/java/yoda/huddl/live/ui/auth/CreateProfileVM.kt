package yoda.huddl.live.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateProfileVM @Inject constructor(val authRepository: AuthRepository)   : ViewModel() {

    private val TAG = "AuthViewModel"

//    @Inject
//    lateinit var authRepository: AuthRepository

//    @Inject
//    lateinit var repository: AuthRepository

//    @Inject
//    lateinit var authApi: AuthApi

    init {
        Log.e(TAG, TAG)
    }

//    fun getAuthState() : LiveData<AuthState> = sessionManager.getAuthUser()

    fun authenticateUser() {
        //authenticate user with auth token and then save token in SessionManager
    }

    fun getIgAuthToken(code: String) = liveData {
        emit( authRepository.getIGAuthToken(
            code = code
        ))
    }

}