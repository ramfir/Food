package com.example.food.data.model

import com.example.food.domain.model.Category

data class RemoteCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

fun RemoteCategory.toDomain() = Category(strCategory)
fun List<RemoteCategory>.toDomain() = map { it.toDomain() }