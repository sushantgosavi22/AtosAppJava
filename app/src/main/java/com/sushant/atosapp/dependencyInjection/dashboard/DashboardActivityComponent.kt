package com.sushant.atosapp.dependencyInjection.dashboard

import com.sushant.atosapp.views.dashboard.ui.DashboardActivity
import dagger.Subcomponent

@Subcomponent(modules = [DashboardActivityModule::class])
interface DashboardActivityComponent {
    fun inject(dashboardActivity: DashboardActivity)

    @Subcomponent.Builder
    interface Builder {
        fun dashboardActivityModule(module: DashboardActivityModule): Builder

        fun build(): DashboardActivityComponent
    }
}
