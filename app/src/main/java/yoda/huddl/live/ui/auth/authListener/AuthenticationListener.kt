package yoda.huddl.live.ui.auth.authListener

interface AuthenticationListener {
    fun onTokenReceived(auth_token: String?)
    fun onCodeReceived(code: String?)
}