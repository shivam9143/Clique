package yoda.huddl.live.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.multibindings.IntKey
import kotlinx.coroutines.launch
import yoda.huddl.live.SessionManager
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val authRepository: AuthRepository, val sessionManager: SessionManager)   : ViewModel() {

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

    fun getAuthState() : LiveData<AuthState> = sessionManager.getAuthUser()

    fun authenticateUser() {
        //authenticate user with auth token and then save token in SessionManager

    }

    fun getIgAuthToken(code: String) = liveData {
        emit( authRepository.getIGAuthToken(
            code = code
        ))
    }

    fun getIgUserProfile(token: String) = liveData {
        emit( authRepository.getIgUserProfile(token))
    }

    fun authenticateUser(token : String) = liveData {
        emit(authRepository.authenticateUser(token = token))
    }

}