package io.github.importre.adkr.api

import io.github.importre.adkr.api.recent.RecentResult
import io.github.importre.adkr.api.user.UserResult
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

public interface Instagram {

    @GET("/v1/users/{user_id}/")
    fun user(@Path("user_id") userId: String = "self",
             @Query("access_token") token: String): Observable<UserResult>

    @GET("/v1/users/{user_id}/media/recent/")
    fun recent(@Path("user_id") userId: String = "self",
               @Query("access_token") token: String): Observable<RecentResult>
}
