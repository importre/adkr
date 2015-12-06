package io.github.importre.adkr.app;

import android.app.Application;

public class App extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}
