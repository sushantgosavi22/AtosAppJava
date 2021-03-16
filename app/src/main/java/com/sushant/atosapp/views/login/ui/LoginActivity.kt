package com.sushant.atosapp.views.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sushant.atosapp.R
import com.sushant.atosapp.application.App
import com.sushant.atosapp.databinding.ActivityLoginBinding
import com.sushant.atosapp.dependencyInjection.login.LoginActivityModule
import com.sushant.atosapp.views.base.BaseActivity
import com.sushant.atosapp.views.dashboard.ui.DashboardActivity
import com.sushant.atosapp.views.login.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : BaseActivity(){

    @Inject
    lateinit var loginViewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        injectDependency()
        binding.loginViewModel = loginViewModel
        setUpView()
    }

    private fun injectDependency() {
        App.getAppComponent()
            .loginActivityComponentBuilder()
            .loginActivityModule(LoginActivityModule(this))
            .build().inject(this)
    }

    private fun setUpView(){
        loginViewModel.enableLogin.observe(this, Observer {
            binding.btnLoginButton.isEnabled = it
        })
        loginViewModel.errorMessage.observe(this, Observer {
            binding.tvError.text = it
        })
        binding.btnLoginButton.setOnClickListener {
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }
    }
}