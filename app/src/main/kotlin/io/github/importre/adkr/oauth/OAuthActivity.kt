package io.github.importre.adkr.oauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.importre.adkr.R
import kotlinx.android.synthetic.activity_oauth.*

class OAuthActivity : AppCompatActivity(), OAuthView {

    private lateinit var clientId: String
    private lateinit var oauthUrl: String
    private lateinit var redirectUrl: String

    private val presenter: OAuthPresenter by lazy {
        OAuthPresenter(this)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth)
        if (initOAuthInfo()) {
            initUi()
        }
    }

    private fun initOAuthInfo(): Boolean {
        intent?.run {
            clientId = getStringExtra("client_id") ?: return false
            oauthUrl = getStringExtra("oauth_url") ?: return false
            redirectUrl = getStringExtra("redirect_url") ?: return false
            return true
        }
        return false
    }

    private fun initUi() {
        webview.settings.javaScriptEnabled = true
        webview.clearCache(true)
        webview.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                presenter.parseToken(url)
            }
        })

        var url = "$oauthUrl?client_id=$clientId&redirect_uri=$redirectUrl&response_type=token"
        webview.loadUrl(url)
    }

    override fun onSuccess(token: String) {
        val data = Intent()
        data.putExtra("token", token)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onFailure() {
    }
}
