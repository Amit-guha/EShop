package com.example.emartket.data.api

import com.example.e_marketapplication.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}