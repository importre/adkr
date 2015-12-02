package io.github.importre.adkr.oauth

import android.net.Uri

class OAuthPresenter(private val view: OAuthView) {

    fun parseToken(url: String?) {
        url?.run {
            val uri = Uri.parse(url)
            uri.fragment?.replace("access_token=", "")?.let {
                view.onSuccess(it)
                return
            }
        }
        view.onFailure()
    }
}