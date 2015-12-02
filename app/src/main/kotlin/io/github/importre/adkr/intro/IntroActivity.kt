package io.github.importre.adkr.intro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import io.github.importre.adkr.BaseActivity
import io.github.importre.adkr.BuildConfig
import io.github.importre.adkr.R
import io.github.importre.adkr.user.UserActivity
import io.github.importre.adkr.util.PrefUtils
import kotlinx.android.synthetic.activity_main.*

class IntroActivity : BaseActivity(), IntroView {

    companion object {
        val REQUEST_OAUTH = 100
    }

    private val presenter: IntroPresenter by lazy {
        IntroPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (accessToken.isNotEmpty()) {
            showUser()
        } else {
            initUi()
        }
    }

    private fun initUi() {
        fab.setOnClickListener { view ->
            val clientId = BuildConfig.CLIENT_ID
            val redirectUrl = BuildConfig.REDIRECT_URL
            val oauthUrl = BuildConfig.OAUTH_URL
            presenter.login(this, REQUEST_OAUTH, clientId, redirectUrl, oauthUrl)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_OAUTH && resultCode == Activity.RESULT_OK && data != null) {
            val token = data.getStringExtra("token")
            PrefUtils.setAccessToken(this, token)
            showUser()
        }
    }

    override fun showUser() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
        finish()
    }
}
