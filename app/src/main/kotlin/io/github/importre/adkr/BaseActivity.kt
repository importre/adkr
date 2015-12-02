package io.github.importre.adkr

import android.support.v7.app.AppCompatActivity
import io.github.importre.adkr.app.App
import io.github.importre.adkr.app.AppComponent
import io.github.importre.adkr.util.PrefUtils

abstract class BaseActivity : AppCompatActivity() {

    protected val accessToken: String by lazy {
        PrefUtils.getAccessToken(this)
    }

    protected val app: App by lazy {
        application as App
    }

    protected val component: AppComponent by lazy {
        app.component
    }
}