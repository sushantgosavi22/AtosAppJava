package com.sushant.atosapp.views.dashboard.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sushant.atosapp.R
import com.sushant.atosapp.application.App
import com.sushant.atosapp.constant.Utils
import com.sushant.atosapp.databinding.ActivityDashboardBinding
import com.sushant.atosapp.dependencyInjection.dashboard.DashboardActivityModule
import com.sushant.atosapp.model.FeedItem
import com.sushant.atosapp.model.FeedResponse
import com.sushant.atosapp.service.model.ApiResponse
import com.sushant.atosapp.service.model.Status
import com.sushant.atosapp.views.adapter.BaseViewHolder
import com.sushant.atosapp.views.adapter.FeedViewHolder
import com.sushant.atosapp.views.adapter.ItemAdapter
import com.sushant.atosapp.views.base.BaseActivity
import com.sushant.atosapp.views.dashboard.viewmodel.DashboardViewModel
import javax.inject.Inject

class DashboardActivity : BaseActivity(), ItemAdapter.IAdapterItemListener<FeedItem> {

    @Inject
    lateinit var dashboardViewModel: DashboardViewModel

    lateinit var binding: ActivityDashboardBinding

    private val adapter by lazy {
        ItemAdapter(ArrayList(),this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        injectDependency()
        binding.lifecycleOwner = this
        binding.viewModel = dashboardViewModel
        initAdapter()
        requestFeed()
    }

    private fun injectDependency() {
        App.getAppComponent()
            .dashboardActivityComponentBuilder()
            .dashboardActivityModule(DashboardActivityModule(this))
            .build().inject(this)
    }

    private fun requestFeed() {
        dashboardViewModel.getFeedApiResponse().observe(this, Observer {
            consumeResponse(it)
        })

        /**
         * Below code check if there is data available in persisted state.
         * In the case of activity recreated eg. screen rotation, config changes
         * Then restore the persisted data [remove extra api/database call ]
         */
        if(dashboardViewModel.isPersistedAvailable().value==false){
            dashboardViewModel.getFeeds()
            dashboardViewModel.setPersisted(true)
        }
    }

    /*
     * method to handle response
     * */
    private fun consumeResponse(apiResponse: ApiResponse<FeedResponse>?) {
        when (apiResponse?.status) {
            Status.LOADING -> dashboardViewModel.onShowLoading()
            Status.CLEAR_LIST_HIDE_ERROR -> {
                showErrorView(false)
            }
            Status.SHOW_EMPTY_LIST -> showErrorView(true)
            Status.SUCCESS -> {
                showErrorView(false)
                dashboardViewModel.onStopLoading()
                val feedResponse =apiResponse.response as FeedResponse
                handleSuccessResponse(feedResponse)
            }
            Status.ERROR -> {
                dashboardViewModel.onStopLoading()
                showErrorView(true)
                Utils.showToast(this, apiResponse.error?.message)
            }
            else ->{
                showErrorView(false)
                dashboardViewModel.onStopLoading()
            }
        }
    }

    private fun handleSuccessResponse(feedResponse : FeedResponse?){
        feedResponse?.results?.let { adapter.setList(it) }
    }

    private fun showErrorView(error:Boolean){
        binding.errorView.visibility = if(error) View.VISIBLE else View.GONE
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(this,2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener{
            dashboardViewModel.onRefresh()
            requestFeed()
        }
    }

    override fun onItemClick(pos: Int, data: FeedItem?) {}
    override fun getHolder(parent: ViewGroup): BaseViewHolder<FeedItem> {
        return FeedViewHolder.getInstance(parent)
    }



}