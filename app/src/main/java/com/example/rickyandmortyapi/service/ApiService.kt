package com.example.rickyandmortyapi.service

import com.example.rickyandmortyapi.model.TesteResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun fetchAllUsers(): Call<TesteResponse>
}