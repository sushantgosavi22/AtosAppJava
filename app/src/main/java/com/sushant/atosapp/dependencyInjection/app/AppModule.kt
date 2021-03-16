package com.sushant.atosapp.dependencyInjection.app

import com.sushant.atosapp.service.clients.APIClient
import com.sushant.atosapp.service.interfaces.ApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return APIClient.apiServices
    }
}
