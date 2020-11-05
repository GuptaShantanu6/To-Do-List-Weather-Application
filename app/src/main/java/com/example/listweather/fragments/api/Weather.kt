package com.example.listweather.fragments.api

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)