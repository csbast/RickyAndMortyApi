package com.example.rickyandmortyapi.domain.model

data class Character(
    val gender: String,
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val status: String,
)