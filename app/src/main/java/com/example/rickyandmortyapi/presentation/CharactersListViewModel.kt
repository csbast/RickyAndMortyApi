package com.example.rickyandmortyapi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickyandmortyapi.domain.model.Character
import com.example.rickyandmortyapi.domain.model.ApiResponse
import com.example.rickyandmortyapi.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CharactersListViewModel @Inject constructor() : ViewModel() {

    private val _characterListLiveData = MutableLiveData<List<Character>>()
    val characterListLiveData: LiveData<List<Character>> get() = _characterListLiveData

    @Inject
    @Named("apiService")
    lateinit var apiService: ApiService

   fun setupNetwork() {
       apiService.fetchAllUsers().enqueue(object : Callback<ApiResponse> {
           override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
               response.body().let {
                   val apiResponse = response.body()
                   val characters = apiResponse?.results
                   Log.d("###", "Retrofit onSucess: ${characters.toString()}")
                   _characterListLiveData.postValue(characters!!)
               }
           }

           override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
               Log.d("###", "Retrofit on Failure")
           }
       })

    }
}