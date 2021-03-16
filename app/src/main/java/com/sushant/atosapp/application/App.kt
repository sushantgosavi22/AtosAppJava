package com.sushant.atosapp.application

import android.app.Application
import android.content.Context
import com.sushant.atosapp.dependencyInjection.app.AppComponent
import com.sushant.atosapp.dependencyInjection.app.AppModule
import com.sushant.atosapp.dependencyInjection.app.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        component = createComponent()
    }


    private fun createComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule()).build()
    }



    companion object {
        private lateinit var context: Context
        lateinit var component: AppComponent
        fun getApplicationContext(): Context {
            return context
        }

        fun getAppComponent(): AppComponent{
            return component
        }
    }
}