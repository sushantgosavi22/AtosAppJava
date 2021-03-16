package com.sushant.atosapp.service.provider

import com.sushant.atosapp.model.FeedResponse
import io.reactivex.Single

interface IServiceProvider {
    fun getFeeds(page: Int): Single<FeedResponse>
}