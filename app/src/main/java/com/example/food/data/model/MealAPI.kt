package com.example.food.data.model

import com.example.food.domain.model.Meal

data class MealAPI(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String,
    val strIngredient1: String = "",
    val strIngredient2: String = "",
    val strIngredient3: String = ""
)

fun MealAPI.toDomain() = Meal(
    idMeal,
    strMeal,
    "$strIngredient1, $strIngredient2, $strIngredient3 ...",
    strMealThumb
)

fun List<MealAPI>.toDomain() = map { it.toDomain() }