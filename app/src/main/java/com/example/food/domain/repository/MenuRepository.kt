package com.example.food.domain.repository

import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.domain.model.MealIngredient

interface MenuRepository {

    fun getBannerList(): List<Banner>
    suspend fun getCategoryList(): List<Category>
    suspend fun getMealList(category: String): List<Meal>
    suspend fun getMealIngredient(id: String): MealIngredient
}