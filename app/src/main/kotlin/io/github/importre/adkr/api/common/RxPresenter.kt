package io.github.importre.adkr.api.common

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class RxPresenter<M : Result>() {

    var views = arrayListOf<View>()
    var subscription: Subscription? = null
    var request: Observable<M>? = null

    @Synchronized
    public fun isLoading(): Boolean = request != null

    @Synchronized
    public fun attach(view: View) {
        setSubscription()

        if (!views.contains(view)) {
            views.add(view)
        }

        if (!isLoading()) {
            loading(true)
        }
    }

    @Synchronized
    public fun detach(view: View) {
        if (views.contains(view)) {
            views.add(view)
        }
    }

    @Synchronized
    public fun init(o: Observable<M>, force: Boolean = false) {
        if (request == null || force) {
            request = o
        }
    }

    fun setSubscription() {
        request?.run {
            subscription = subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        next(result)
                    }, { error ->
                        failure(error)
                    }, {
                        complete()
                    })
        }
    }

    @Synchronized
    fun loading(show: Boolean) {
        views.forEach { it.showLoading(show) }
    }

    @Synchronized
    fun next(result: M) {
        views.forEach { it.showResult(result) }
    }

    @Synchronized
    fun failure(error: Throwable) {
        views.forEach { it.showError(error) }
    }

    @Synchronized
    fun complete() {
        request = null
        loading(false)
    }
}