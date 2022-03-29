package com.example.food.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBAPI {

    @GET("categories.php")
    suspend fun getCategories(): TheMealDBCategoryResponse

    @GET("filter.php")
    suspend fun getMealList(@Query("c") category: String): TheMealDBMealResponse

    @GET("lookup.php")
    suspend fun getMealIngredient(@Query("i") id: String): TheMealDBMealResponse
}