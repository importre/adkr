package io.github.importre.adkr.oauth

interface OAuthView {

    fun onSuccess(token: String)

    fun onFailure()
}

