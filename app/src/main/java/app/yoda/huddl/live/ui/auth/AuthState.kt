package app.yoda.huddl.live.ui.auth

import androidx.lifecycle.MutableLiveData
import app.yoda.huddl.live.models.User

sealed class AuthState
object AuthStateIdle : AuthState()
class AuthStateAuthenticated(val data: User) : AuthState()
class AuthStateError(val errorMsg: String? = null) : AuthState()
object AuthStateLoading : AuthState()
object AuthStateNotAuthenticated : AuthState()

class AuthStateLiveData(state: AuthState = AuthStateIdle) : MutableLiveData<AuthState>() {
    init {
        value = state
    }
}

