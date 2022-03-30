package com.example.food.data.model

import com.example.food.domain.model.Category

data class CategoryAPI(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

fun CategoryAPI.toDomain() = Category(strCategory)
fun List<CategoryAPI>.toDomain() = map { it.toDomain() }