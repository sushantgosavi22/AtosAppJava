package com.sushant.atosapp.views.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.sushant.atosapp.constant.Constants
import com.sushant.atosapp.constant.Utils


open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected var page = Constants.FIRST_PAGE
    /**
     * Observer SwipeToRefresh
     */
    val isLoading = ObservableBoolean()

    /**
     * Show SwipeToRefresh.
     */
    fun onShowLoading() = isLoading.set(true)

    /**
     * Hide SwipeToRefresh.
     */
    fun onStopLoading() = isLoading.set(false)

    /**
     * increment Page Count value.
     */
    fun incrementPageCount() = page++

    fun resetPageCount()  {
        page =Constants.FIRST_PAGE
    }
    override fun onCleared() {
        super.onCleared()
    }
}