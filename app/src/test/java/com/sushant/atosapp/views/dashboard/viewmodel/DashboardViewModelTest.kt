package com.sushant.atosapp.views.dashboard.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.sushant.atosapp.model.FeedItem
import com.sushant.atosapp.model.FeedResponse
import com.sushant.atosapp.repositorys.feedrepo.FeedRepository
import com.sushant.atosapp.service.model.ApiResponse
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest : TestCase() {
    private val page =1
    private lateinit var feedResponse: FeedResponse
    private lateinit var list: ArrayList<FeedItem>
    private lateinit var viewModel: DashboardViewModel
    private lateinit var apiResponseObserver : Observer<ApiResponse<FeedResponse>>
    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var repository: FeedRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        repository = Mockito.mock(FeedRepository::class.java)
        viewModel = DashboardViewModel(context,repository, SavedStateHandle())
        apiResponseObserver = Mockito.mock(Observer::class.java) as Observer<ApiResponse<FeedResponse>>
        mockRequiredData()
    }


    @Test
    fun testGetFeedSuccessScenario(){
        viewModel.mApiResponseTest.observeForever(apiResponseObserver)
        Mockito.`when`(repository.getFeeds(page)).thenReturn(Single.just(feedResponse))
        viewModel.getFeeds(page)

        Mockito.verify(repository, Mockito.times(1)).getFeeds(page)
        val response = viewModel.mApiResponseTest.value?.response as FeedResponse
        Assert.assertEquals(response.results?.size, 3)
    }


    @Test(expected = Exception::class)
    fun testGetFeedFailedScenario(){
        viewModel.mApiResponseTest.observeForever(apiResponseObserver)
        Mockito.doThrow(Throwable()).`when`(repository).getFeeds(page)
        viewModel.getFeeds(page)
        Assert.assertNull(viewModel.mApiResponseTest.value?.response)
    }



    @Test
    fun testOnRefreshScenario(){
        viewModel.mApiResponseTest.observeForever(apiResponseObserver)
        Mockito.`when`(repository.getFeeds(page)).thenReturn(Single.just(feedResponse))
        viewModel.onRefresh()

        Mockito.verify(repository, Mockito.times(1)).getFeeds(page)
        val response = viewModel.mApiResponseTest.value?.response as FeedResponse
        Assert.assertEquals(response.results?.size, 3)
    }


    private fun mockRequiredData() {
        list = ArrayList()
        list.add(FeedItem().apply {
                this.poster_path=""
                this.original_title=""
                this.overview=""
            }
        )
        list.add(
            FeedItem().apply {
                this.poster_path=""
                this.original_title=""
                this.overview=""
            }
        )
        list.add(
            FeedItem().apply {
                this.poster_path=""
                this.original_title=""
                this.overview=""
            }
        )
        feedResponse = FeedResponse().apply {
            results =list
        }
    }

}