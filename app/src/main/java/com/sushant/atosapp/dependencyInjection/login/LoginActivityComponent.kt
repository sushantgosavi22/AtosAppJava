package com.sushant.atosapp.dependencyInjection.login

import com.sushant.atosapp.views.login.ui.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [LoginActivityModule::class])
interface LoginActivityComponent {
    fun inject(loginActivity: LoginActivity)

    @Subcomponent.Builder
    interface Builder {
        fun loginActivityModule(module: LoginActivityModule): Builder

        fun build(): LoginActivityComponent
    }
}
