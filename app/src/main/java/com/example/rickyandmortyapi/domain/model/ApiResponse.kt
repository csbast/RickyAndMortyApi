package com.example.rickyandmortyapi.domain.model

data class ApiResponse(
    val info: Info,
    val results: List<Character>
)