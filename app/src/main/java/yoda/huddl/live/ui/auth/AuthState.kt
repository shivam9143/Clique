package yoda.huddl.live.ui.auth

import androidx.lifecycle.MutableLiveData

sealed class AuthState
object AuthStateIdle : AuthState()
object AuthStatePhoneAuthenticated : AuthState()
object AuthStateInstagramAuthenticated : AuthState()
class AuthStateError(val errorMsg: String? = null) : AuthState()
object AuthStateLoading : AuthState()
object AuthStateNotAuthenticated : AuthState()

class AuthStateLiveData(state: AuthState = AuthStateIdle) : MutableLiveData<AuthState>() {
    init {
        value = state
    }
}

