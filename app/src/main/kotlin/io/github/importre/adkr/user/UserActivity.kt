package io.github.importre.adkr.user

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.importre.adkr.BaseActivity
import io.github.importre.adkr.R
import io.github.importre.adkr.api.Instagram
import io.github.importre.adkr.api.common.Result
import io.github.importre.adkr.api.common.View
import io.github.importre.adkr.api.recent.RecentResult
import io.github.importre.adkr.api.user.UserResult
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject

class UserActivity : BaseActivity(), View {

    @Inject lateinit var instagram: Instagram

    val userPresenter: UserPresenter by lazy { UserPresenter.get() }
    val recentPresenter: RecentPresenter by lazy { RecentPresenter.get() }
    val adapter: RecentAdapter by lazy { RecentAdapter(this, arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initUi()

        component.inject(this@UserActivity)
        userPresenter.init(instagram.user(token = accessToken).cache())
        userPresenter.attach(this)
        recentPresenter.init(instagram.recent(token = accessToken).cache())
        recentPresenter.attach(this)
    }

    private fun initUi() {
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        userPresenter.detach(this)
        recentPresenter.detach(this)
        super.onDestroy()
    }

    override fun showLoading(show: Boolean) {
        if (show && (userPresenter.isLoading() || recentPresenter.isLoading())) {
            toolbar.subtitle = "loading"
        } else {
            toolbar.subtitle = ""
        }
    }

    override fun showResult(result: Result) {
        when (result) {
            is UserResult -> updateUser(result)
            is RecentResult -> updateRecent(result)
        }
    }

    private fun updateUser(result: UserResult) {
        if (result.meta.code == 200) {
            toolbar.title = result.data.userName
        }
    }

    private fun updateRecent(result: RecentResult) {
        if (result.meta.code == 200) {
            adapter.data.clear()
            adapter.data.addAll(result.data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError(error: Throwable) {
    }
}