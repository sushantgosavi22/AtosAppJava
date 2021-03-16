package com.sushant.atosapp.dependencyInjection.app

import com.sushant.atosapp.dependencyInjection.dashboard.DashboardActivityComponent
import com.sushant.atosapp.dependencyInjection.login.LoginActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun dashboardActivityComponentBuilder(): DashboardActivityComponent.Builder
    fun loginActivityComponentBuilder(): LoginActivityComponent.Builder
}
