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

    fun getstrore(){
        viewModelScope.launch {
            val response = repository.getStore()
            myResponse.value = response
        }
    }


}