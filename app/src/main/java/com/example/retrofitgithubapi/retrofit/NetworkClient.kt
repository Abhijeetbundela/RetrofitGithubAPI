package com.example.retrofitgithubapi.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {

    private const val BASE_URL = "https://api.github.com/"

    private val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val oktHttpClient =
            OkHttpClient.Builder().addInterceptor(interceptor)

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient.build())
            .baseUrl(BASE_URL)
            .build()

    fun create(): APIs {
        return retrofit.create(APIs::class.java)
    }
}