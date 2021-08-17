package app.yoda.huddl.live

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import app.yoda.huddl.live.ui.auth.AuthState
import app.yoda.huddl.live.ui.auth.AuthStateError
import app.yoda.huddl.live.ui.auth.AuthStateLoading
import app.yoda.huddl.live.ui.auth.AuthStateNotAuthenticated
import javax.inject.Inject


class SessionManager @Inject constructor() {

    var TAG = "SessionManager"

    var cachedUser: MediatorLiveData<AuthState> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthState>) {
        if (cachedUser != null) {
            cachedUser.value = AuthStateLoading
            cachedUser.addSource(source
            ) { userAuthState ->
                cachedUser.value = userAuthState
                cachedUser.removeSource(source)
                if (userAuthState == AuthStateError()) {
                    cachedUser.value = AuthStateNotAuthenticated
                }
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthStateNotAuthenticated
    }

    fun getAuthUser(): LiveData<AuthState> = cachedUser
}