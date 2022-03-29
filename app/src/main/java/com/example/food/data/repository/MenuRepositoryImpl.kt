package com.example.food.data.repository

import com.example.food.R
import com.example.food.data.api.TheMealDBAPI
import com.example.food.data.model.toDomain
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.domain.model.MealIngredient
import com.example.food.domain.repository.MenuRepository

class MenuRepositoryImpl(private val api: TheMealDBAPI): MenuRepository {

    override fun getBannerList(): List<Banner> = listOf(
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner),
        Banner(R.drawable.img_pizza_banner)
    )

    /*override fun getCategoryList(): List<Category> {
        return listOf(
            Category("Pizza"),
            Category("First meal"),
            Category("Second meal"),
            Category("Sea food"),
            Category("Dring"),
            Category("Desert"),
        )
    }*/

    /*override fun getMealList(category: Category): List<Meal> {
        return listOf(
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
            Meal("Ветчина и грибы ", "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус", "от 345 р", R.drawable.img_pizza),
        )
    }*/
    override suspend fun getCategoryList(): List<Category> {
        return api.getCategories().categories.toDomain()
    }

    override suspend fun getMealList(category: String): List<Meal> {
        return api.getMealList(category).meals.toDomain()
    }

    override suspend fun getMealIngredient(id: String): MealIngredient {
        val mealIngredientDescription = api.getMealIngredient(id).meals[0].toDomain().ingredients// "${api.getMealIngredient(id).meals[0].strIngredient1}, ${api.getMealIngredient(id).meals[0].strIngredient2}, ${api.getMealIngredient(id).meals[0].strIngredient3}..."
        return MealIngredient(id, mealIngredientDescription)
    }
}