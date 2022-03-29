package com.example.food.presentation.menu.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.domain.menu.MenuInteractor
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import kotlinx.coroutines.launch

class MenuViewModel(private val menuInteractor: MenuInteractor) : ViewModel() {

    val bannerList = MutableLiveData(menuInteractor.getBannerList())
    val categoryList: MutableLiveData<List<Category>> = MutableLiveData()
    val mealList:MutableLiveData<List<Meal>> = MutableLiveData()

    fun getCategoryList() {
        viewModelScope.launch {
            categoryList.value = menuInteractor.getCategoryList()
        }
    }

    fun getMealList(category: String) {
        viewModelScope.launch {
            val mealListFromAPI = menuInteractor.getMealList(category)
            getMealIngredients(mealListFromAPI)
            mealList.value = mealListFromAPI
        }
    }

    private suspend fun getMealIngredients(mealListFromAPI: List<Meal>) {
        for (meal in mealListFromAPI) {
            meal.ingredients = menuInteractor.getMealIngredient(meal.id).description
        }
    }

}