package com.example.e_marketapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.e_marketapplication.data.model.PostStoreInfo
import com.example.emartket.data.api.RetrofitBuilder
import com.example.emartket.data.model.PostItem
import com.example.emartket.data.model.PostProductsItem

import retrofit2.Response

class MainRepository {
    suspend fun getStore() : Response<PostStoreInfo>{
        return RetrofitBuilder.apiService.getStore()
    }


}