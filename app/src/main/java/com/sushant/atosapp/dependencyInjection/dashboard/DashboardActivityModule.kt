package com.sushant.atosapp.dependencyInjection.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProviders
import com.sushant.atosapp.repositorys.feedrepo.FeedRepository
import com.sushant.atosapp.service.interfaces.ApiInterface
import com.sushant.atosapp.service.provider.IServiceProvider
import com.sushant.atosapp.service.provider.ServiceProvider
import com.sushant.atosapp.views.dashboard.ui.DashboardActivity
import com.sushant.atosapp.views.dashboard.viewmodel.DashboardViewModel
import dagger.Module
import dagger.Provides

@Module
class DashboardActivityModule(private val activity: DashboardActivity) {

    @Provides
    fun provideIServiceProvider(apiServices: ApiInterface): IServiceProvider {
        return ServiceProvider(apiServices)
    }

    @Provides
    fun provideFeedRepository(mIServiceProvider: IServiceProvider): FeedRepository {
        return FeedRepository(mIServiceProvider)
    }


    @Provides
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    @Provides
    fun provideDashboardViewModel(
        feedRepository: FeedRepository,
        savedStateHandle: SavedStateHandle
    ): DashboardViewModel {
        return ViewModelProviders.of(
            activity,
            DashboardViewModel.DashboardViewModelFactory(
                activity.application,
                feedRepository,
                savedStateHandle
            )
        ).get(DashboardViewModel::class.java)
    }

}
