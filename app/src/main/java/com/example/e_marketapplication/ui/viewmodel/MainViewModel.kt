package com.example.emartket.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_marketapplication.data.model.PostStoreInfo
import com.example.e_marketapplication.data.repository.MainRepository
import com.example.emartket.data.model.PostItem

import com.example.emartket.data.model.PostProductsItem

import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<PostStoreInfo>> = MutableLiveData()
    val myProducts : MutableLiveData<Response<List<PostProductsItem>>> = MutableLiveData()
    val myResponse3 : MutableLiveData<Response<PostItem>> = MutableLiveData()

    fun getstrore(){
        viewModelScope.launch {
            val response = repository.getStore()
            myResponse.value = response
        }
    }

    fun getProduct(){
        viewModelScope.launch {
            val response2 = repository.getProduct()
            myProducts.value= response2
        }
    }

    fun pushPost(postItem: PostItem){
        viewModelScope.launch {
            val response3 = repository.pushPost(postItem)
            myResponse3.value = response3
        }
    }


}