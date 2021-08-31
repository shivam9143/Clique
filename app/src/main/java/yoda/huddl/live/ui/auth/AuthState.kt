package yoda.huddl.live.ui.auth

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.UserProfileChangeRequest
import yoda.huddl.live.models.User
import yoda.huddl.live.models.UserProfile

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

