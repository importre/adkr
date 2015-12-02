package io.github.importre.adkr.intro

import android.app.Activity
import android.content.Intent
import io.github.importre.adkr.oauth.OAuthActivity

class IntroPresenter(private val view: IntroView) : IntroAction {

    override fun login(activity: Activity, requestCode: Int,
                       clientId: String, redirectUrl: String, oauthUrl: String) {
        val intent = Intent(activity, OAuthActivity::class.java)
        intent.putExtra("client_id", clientId)
        intent.putExtra("redirect_url", redirectUrl)
        intent.putExtra("oauth_url", oauthUrl)
        activity.startActivityForResult(intent, requestCode)
    }
}