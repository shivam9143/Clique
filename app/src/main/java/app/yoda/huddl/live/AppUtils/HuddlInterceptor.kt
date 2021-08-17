package app.yoda.huddl.live.AppUtils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class HuddlInterceptor(var signInTokenManager: SignInTokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val response : Response
        response = if (signInTokenManager.isSignedin()) {
            val token = signInTokenManager.token
            val headerVal = "Token ${token}"
            val request = original.newBuilder()
                .header("Authorization", headerVal)
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        } else
            chain.proceed(original)
        return response
    }
}