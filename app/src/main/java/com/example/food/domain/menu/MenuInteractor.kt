package com.example.food.domain.menu

import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.domain.repository.MenuRepository

class MenuInteractor(private val menuRepository: MenuRepository) {

    fun getBannerList(): List<Banner> = menuRepository.getBannerList()
    suspend fun getCategoryList(): List<Category> = menuRepository.getCategoryList()
    suspend fun getMealList(category: String): List<Meal> = menuRepository.getMealList(category)
}