package com.example.food.data.repository

import com.example.food.R
import com.example.food.data.api.TheMealDBAPI
import com.example.food.data.model.toDomain
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.domain.model.MealIngredient
import com.example.food.domain.repository.MenuRepository

class MenuRepositoryImpl(private val api: TheMealDBAPI) : MenuRepository {

    override fun getBannerList(): List<Banner> = listOf(
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner)
    )

    override suspend fun getCategoryList() = api.getCategories().categories.toDomain()
    override suspend fun getMealList(category: String) = api.getMealList(category).meals.toDomain()

    override suspend fun getMealIngredient(id: String): MealIngredient {
        val mealIngredientDescription = api.getMealIngredient(id).meals[0].toDomain().ingredients
        return MealIngredient(id, mealIngredientDescription)
    }
}