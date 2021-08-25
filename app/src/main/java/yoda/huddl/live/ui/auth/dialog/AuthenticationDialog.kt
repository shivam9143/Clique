package yoda.huddl.live.ui.auth.dialog

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.facebook.internal.Utility.isNullOrEmpty
import com.google.android.gms.common.config.GservicesValue.value
import com.google.android.gms.common.util.Strings
import yoda.huddl.live.R
import yoda.huddl.live.ui.auth.authListener.AuthenticationListener
import java.net.URL
import java.net.URLDecoder
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Collectors.mapping
import java.util.stream.Collectors.toList
import kotlin.collections.LinkedHashMap


class AuthenticationDialog constructor(context: Context, val listener: AuthenticationListener?) :
    Dialog(context) {


    private var redirect_url: String? = null
    private var request_url: String? = null
//    ttps://api.instagram.com/oauth/authorize
//    ?client_id={instagram-app-id}
//    &redirect_uri={redirect-uri}
//    &scope={scope}
//    &response_type=code
//    &state={state}        //Optional
    init {
        redirect_url = context.resources.getString(R.string.callback_url)
        request_url = context.resources.getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                context.resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url +
                "&response_type=code&scope=user_profile,user_media"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.auth_dialog)
        initDialogWebView()
    }


    private fun initDialogWebView() {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        request_url?.let { webView.loadUrl(it) }
        webView.webViewClient = object : WebViewClient() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (redirect_url?.let { request?.url?.toString()?.startsWith(it) } == true) {
                    dismiss();
                    return true;
                }
                return false;
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                var dec_url = URLDecoder.decode(url, "UTF-8")
                val uri: Uri = Uri.parse(dec_url)
                if (uri.toString()!!.contains("code=")) {
                    var map = getQueryKeyValueMap(Uri.parse(getQueryKeyValueMap(uri = Uri.parse(url)).get("u")))
                    var code: String? = map.get("code")
                    code = code?.substring(code.lastIndexOf("=") + 1)
                    listener!!.onCodeReceived(code)
                }
                if (uri.toString()!!.contains("access_token=")) {
                    val uri: Uri = Uri.parse(url)
                    var access_token: String? = uri.encodedFragment
                    access_token = access_token?.substring(access_token.lastIndexOf("=") + 1)
                    listener!!.onTokenReceived(access_token)
                }
            }
        }
    }


    fun getQueryKeyValueMap(uri: Uri): HashMap<String, String> {
        val keyValueMap = HashMap<String, String>()
        var key: String
        var value: String

        val keyNamesList = uri.queryParameterNames
        val iterator = keyNamesList.iterator()

        while (iterator.hasNext()) {
            key = iterator.next() as String
            value = uri.getQueryParameter(key) as String
            keyValueMap.put(key, value)
        }
        return keyValueMap
    }

}
