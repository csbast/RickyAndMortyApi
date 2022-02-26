package com.example.rickyandmortyapi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickyandmortyapi.model.ApiResponse
import com.example.rickyandmortyapi.model.Character
import com.example.rickyandmortyapi.service.ApiService
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
        if(apiService != null) {
            apiService.fetchAllUsers().enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    response.body().let {
                        val apiResponse = response.body()
                        val characters = apiResponse?.charactersList
                        Log.d("###", "Retrofit onSucess: ${characters.toString()}")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("###", "Retrofit on Failure")
                }
            })
        }

    }
}