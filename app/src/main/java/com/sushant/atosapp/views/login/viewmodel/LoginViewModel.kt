package com.sushant.atosapp.views.login.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.sushant.atosapp.R
import com.sushant.atosapp.application.App
import com.sushant.atosapp.constant.Constants.MIN_PASSWORD_LENGTH
import com.sushant.atosapp.views.base.BaseViewModel

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var enableLogin = MutableLiveData<Boolean>(false)
    var errorMessage = MutableLiveData<String>()
    var isValidMail = false
    var isValidPassword = false

    fun onMailTextChanged(mail: CharSequence, start: Int, before: Int, count: Int) {
        isValidMail = mail.isValidEmail()
        validate()
    }

    fun onPasswordTextChanged(password: CharSequence, start: Int, before: Int, count: Int) {
        isValidPassword = password.isValidPassword()
        validate()
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun CharSequence?.isValidPassword() =
        !isNullOrEmpty() && this?.length ?: 0 >= MIN_PASSWORD_LENGTH


    private fun validate() {
        val result =isValidMail && isValidPassword
        enableLogin.value =result
        errorMessage.value = when {
            result -> {
                getApplication<App>().getString(R.string.welcome)
            }
            isValidMail.not() -> {
                getApplication<App>().getString(R.string.valid_email)
            }
            isValidPassword.not() -> {
                getApplication<App>().getString(R.string.valid_password)
            }
            else -> {
                getApplication<App>().getString(R.string.welcome)
            }
        }
    }
}