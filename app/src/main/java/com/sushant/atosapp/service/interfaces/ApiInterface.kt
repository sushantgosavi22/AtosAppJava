package com.sushant.atosapp.service.interfaces
import com.sushant.atosapp.model.FeedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("movie/popular")
    fun getFeeds(@Query("page") page: Int?): Single<FeedResponse>

}