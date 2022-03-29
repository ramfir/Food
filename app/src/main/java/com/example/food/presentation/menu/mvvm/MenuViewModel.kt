package com.example.food.presentation.menu.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.domain.menu.MenuInteractor
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import kotlinx.coroutines.launch

class MenuViewModel(private val menuInteractor: MenuInteractor) : ViewModel() {

    private val _bannerList = MutableLiveData(menuInteractor.getBannerList())
    val bannerList: LiveData<List<Banner>> = _bannerList

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = _categoryList

    private val _mealList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> = _mealList

    fun getCategoryList() {
        viewModelScope.launch {
            _categoryList.value = menuInteractor.getCategoryList()
        }
    }

    fun getMealList(category: String) {
        viewModelScope.launch {
            val mealListFromAPI = menuInteractor.getMealList(category)
            getMealIngredients(mealListFromAPI)
            _mealList.value = mealListFromAPI
        }
    }

    private suspend fun getMealIngredients(mealListFromAPI: List<Meal>) {
        mealListFromAPI.forEach { meal ->
            meal.ingredients = menuInteractor.getMealIngredient(meal.id).description
        }
    }
}