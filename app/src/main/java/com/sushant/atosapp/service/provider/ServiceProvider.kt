package com.sushant.atosapp.service.provider

import com.sushant.atosapp.model.FeedResponse
import com.sushant.atosapp.service.interfaces.ApiInterface
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ServiceProvider(private val apiServices: ApiInterface) : IServiceProvider {
    override fun getFeeds(page: Int): Single<FeedResponse> {
        return apiServices.getFeeds(page).subscribeOn(Schedulers.io()).flatMap {
            val filteredList = it.results?.filter {(it.overview==null&&it.original_title==null&&it.poster_path==null ).not()}
            it.results =ArrayList(filteredList )
            Single.just(it)
        }
    }
}