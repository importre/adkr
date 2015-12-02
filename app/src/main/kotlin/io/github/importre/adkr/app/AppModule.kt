package io.github.importre.adkr.app

import android.content.Context
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import io.github.importre.adkr.api.Instagram
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import javax.inject.Singleton

@Module
public class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideInstagram(): Instagram {
        val url: String = "https://api.instagram.com/"
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        client.interceptors().add(interceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(Instagram::class.java)
    }
}

