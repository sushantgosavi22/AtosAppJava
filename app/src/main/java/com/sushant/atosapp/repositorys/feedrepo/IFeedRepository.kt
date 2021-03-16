package com.sushant.atosapp.repositorys.feedrepo

import com.sushant.atosapp.model.FeedResponse
import io.reactivex.Single

interface IFeedRepository {
    fun getFeeds(page: Int) : Single<FeedResponse>
}