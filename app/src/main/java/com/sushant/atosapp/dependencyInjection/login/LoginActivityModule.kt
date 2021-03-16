package com.sushant.atosapp.dependencyInjection.login

import androidx.lifecycle.ViewModelProviders
import com.sushant.atosapp.views.login.ui.LoginActivity
import com.sushant.atosapp.views.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private val activity: LoginActivity) {

    @Provides
    fun provideLoginViewModel(): LoginViewModel {
        return ViewModelProviders.of(activity).get(LoginViewModel::class.java)
    }

}
