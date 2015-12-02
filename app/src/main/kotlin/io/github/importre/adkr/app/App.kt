package io.github.importre.adkr.app

import android.app.Application

class App : Application() {

    public val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
