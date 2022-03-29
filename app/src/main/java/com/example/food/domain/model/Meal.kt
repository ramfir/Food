package com.example.food.domain.model

data class Meal(
    val id: String,
    val name: String,
    var ingredients: String,
    val img: String,
    val price: String = "от 345 р"
)