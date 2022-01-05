package com.example.emartket.data.api

import com.example.e_marketapplication.data.model.PostStoreInfo
import com.example.emartket.data.model.PostItem
import com.example.emartket.data.model.PostProductsItem
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("storeInfo")
    suspend fun getStore() : Response<PostStoreInfo>

}