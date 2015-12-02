package io.github.importre.adkr.intro

import android.app.Activity

interface IntroAction {

    fun login(activity: Activity, requestCode: Int,
              clientId: String, redirectUrl: String, oauthUrl: String)
}

