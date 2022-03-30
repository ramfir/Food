package com.example.food.presentation.menu.model

import com.example.food.domain.model.Category

data class CategoryUI(
    val name: String,
    val isChecked: Boolean = false
)

fun Category.toUI() = CategoryUI(name)
fun List<Category>.toUI() = map { it.toUI() }