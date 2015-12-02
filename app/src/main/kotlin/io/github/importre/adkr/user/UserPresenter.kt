package io.github.importre.adkr.user

import io.github.importre.adkr.api.common.RxPresenter
import io.github.importre.adkr.api.user.UserResult

open class UserPresenter private constructor() : RxPresenter<UserResult>(), UserAction {

    companion object {
        private var self: UserPresenter? = null

        @Synchronized
        fun get(): UserPresenter {
            if (self == null) {
                self = UserPresenter()
            }
            return self!!
        }
    }
}
