package com.example.food.presentation.menu.model

import com.example.food.domain.model.Category

data class UICategory(
    val name: String,
    var isChecked: Boolean = false
)

fun Category.toUI() = UICategory(name)
fun List<Category>.toUI() = map { it.toUI() }