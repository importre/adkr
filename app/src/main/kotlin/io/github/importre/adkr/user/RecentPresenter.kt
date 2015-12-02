package io.github.importre.adkr.user

import io.github.importre.adkr.api.common.RxPresenter
import io.github.importre.adkr.api.recent.RecentResult

open class RecentPresenter private constructor() : RxPresenter<RecentResult>() {

    companion object {
        private var self: RecentPresenter? = null

        @Synchronized
        fun get(): RecentPresenter {
            if (self == null) {
                self = RecentPresenter()
            }
            return self!!
        }
    }
}
