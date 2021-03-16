package com.sushant.atosapp.views.base

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sushant.atosapp.R
import com.sushant.atosapp.constant.Constants
import com.sushant.atosapp.constant.Utils
import java.io.File


object Binder {

    @BindingAdapter("bind:loadUrl")
    @JvmStatic
    public fun bindUrlImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view)
                .load(Constants.IMAGE_URL.plus(url))
                .placeholder(R.drawable.flower)
                .error(R.drawable.flower)
                .into(view)
        }
    }
}