package com.example.rickyandmortyapi.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickyandmortyapi.model.TesteCharacter
import com.example.rickyandmortyapi.model.TesteResponse
import com.example.rickyandmortyapi.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CharactersListViewModel @Inject constructor() : ViewModel() {

    private val _characterListLiveData = MutableLiveData<List<TesteCharacter>>()
    val characterListLiveData: LiveData<List<TesteCharacter>> get() = _characterListLiveData

    @Inject
    @Named("apiService")
    lateinit var apiService: ApiService

   fun setupNetwork() {
       apiService.fetchAllUsers().enqueue(object : Callback<TesteResponse> {
           override fun onResponse(call: Call<TesteResponse>, response: Response<TesteResponse>) {
               response.body().let {
                   val apiResponse = response.body()
                   val characters = apiResponse?.results
                   Log.d("###", "Retrofit onSucess: ${characters.toString()}")
                   _characterListLiveData.postValue(characters!!)
               }
           }

           override fun onFailure(call: Call<TesteResponse>, t: Throwable) {
               Log.d("###", "Retrofit on Failure")
           }
       })

    }
}