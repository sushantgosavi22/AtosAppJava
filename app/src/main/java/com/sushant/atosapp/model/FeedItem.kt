package com.sushant.atosapp.model

import java.io.Serializable

open class FeedItem : Serializable {
    var poster_path: String? = null
    var overview: String? = null
    var original_title: String? = null
}
