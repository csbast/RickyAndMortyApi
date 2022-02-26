package com.example.rickyandmortyapi.service

import com.example.rickyandmortyapi.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/character")
    fun fetchAllUsers(): Call<ApiResponse>
}