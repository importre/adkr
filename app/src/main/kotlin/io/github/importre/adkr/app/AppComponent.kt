package io.github.importre.adkr.app

import dagger.Component
import io.github.importre.adkr.user.UserActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: UserActivity)

    fun inject(any: Any)
}
