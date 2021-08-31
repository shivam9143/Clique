package yoda.huddl.live

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import yoda.huddl.live.Offline.HuddlOfflineDataManager
import yoda.huddl.live.ui.auth.AuthState
import yoda.huddl.live.ui.auth.AuthStateError
import yoda.huddl.live.ui.auth.AuthStateLoading
import yoda.huddl.live.ui.auth.AuthStateNotAuthenticated
import javax.inject.Inject


class SessionManager @Inject constructor() {

    var TAG = "SessionManager"

    var cachedUser: MediatorLiveData<AuthState> = MediatorLiveData()

    var authState : MediatorLiveData<AuthState> = MediatorLiveData()

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

    fun getAuthState()
    {
//        if(HuddlOfflineDataManager.getUserPhoneAuthenticated())
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthStateNotAuthenticated
    }

    fun getAuthUser(): LiveData<AuthState> = cachedUser
}