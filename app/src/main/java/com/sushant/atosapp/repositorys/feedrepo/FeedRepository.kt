package com.sushant.atosapp.repositorys.feedrepo

import com.sushant.atosapp.model.FeedResponse
import com.sushant.atosapp.service.provider.IServiceProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

open class FeedRepository(private var mIServiceProvider: IServiceProvider) : IFeedRepository {
    override fun getFeeds(page: Int): Single<FeedResponse> {
        return mIServiceProvider.getFeeds(page).observeOn(AndroidSchedulers.mainThread())
    }

}