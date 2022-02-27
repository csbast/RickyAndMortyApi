package com.example.rickyandmortyapi.data.service

import com.example.rickyandmortyapi.domain.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun fetchAllUsers(): Call<ApiResponse>
}