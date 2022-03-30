package com.example.food.data.repository

import com.example.food.R
import com.example.food.data.api.TheMealDBAPI
import com.example.food.data.model.toDomain
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.domain.model.MealIngredient
import com.example.food.domain.repository.MenuRepository
import java.io.IOException

class MenuRepositoryImpl(private val api: TheMealDBAPI) : MenuRepository {

    override fun getBannerList(): List<Banner> = listOf(
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner),
        Banner(R.drawable.img_banner)
    )

    override suspend fun getCategoryList(): List<Category> {
        return try {
            api.getCategories().categories.toDomain()
        } catch (ex: IOException) {
            println(ex.message)
            listOf()
        }
    }

    override suspend fun getMealList(category: String): List<Meal> {
        return try {
            val mealList = api.getMealList(category).meals.toDomain()
            mealList.forEach { it.ingredients = getMealIngredient(it.id).description }
            mealList
        } catch (ex: IOException) {
            println(ex.message)
            listOf()
        }
    }

    private suspend fun getMealIngredient(id: String): MealIngredient {
        val mealIngredientDescription = api.getMealIngredient(id).meals[0].toDomain().ingredients
        return MealIngredient(id, mealIngredientDescription)
    }
}