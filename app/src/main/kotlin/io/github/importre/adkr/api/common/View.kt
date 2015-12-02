package io.github.importre.adkr.api.common

interface View {

    fun showLoading(show: Boolean)

    fun showResult(result: Result)

    fun showError(error: Throwable)
}