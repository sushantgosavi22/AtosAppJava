package com.sushant.atosapp.model

import com.sushant.atosapp.service.model.BaseResponse

open class FeedResponse : BaseResponse<FeedResponse>() {
    var results: ArrayList<FeedItem>? = null
    var page: Int? = null
    var total_pages: Int? = null
    var total_results: Int? = null
}
