package com.sushant.atosapp.service.clients

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sushant.atosapp.constant.Constants
import com.sushant.atosapp.constant.Utils
import com.sushant.atosapp.service.interfaces.ApiInterface
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {

    private const val API_KEY_VALUE: String = "dfa8957fc73f43cd2b894c4226e084b5"
    private const val baseURL: String = "https://api.themoviedb.org/3/"

    private val client: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptor())
                .addInterceptor(loggerInterceptor())
                .addInterceptor { chain->
                    val original: Request = chain.request()
                    val originalHttpUrl: HttpUrl = original.url()
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter(Constants.API_KEY, API_KEY_VALUE)
                        .build()
                    val requestBuilder: Request.Builder = original.newBuilder()
                        .url(url)
                    val request: Request = requestBuilder.build()
                    chain.proceed(request)
                }
            .build()
    }

    private fun loggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    val apiServices: ApiInterface by lazy { client.create(ApiInterface::class.java) }
}