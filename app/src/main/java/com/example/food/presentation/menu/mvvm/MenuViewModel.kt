package com.example.food.presentation.menu.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.domain.menu.MenuInteractor
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Category
import com.example.food.domain.model.Meal
import com.example.food.presentation.menu.model.UICategory
import com.example.food.presentation.menu.model.toUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MenuViewModel(private val menuInteractor: MenuInteractor) : ViewModel() {

    private val _bannerList = MutableLiveData(menuInteractor.getBannerList())
    val bannerList: LiveData<List<Banner>> = _bannerList

    private val _categoryList = MutableLiveData<List<UICategory>>()
    val categoryList: LiveData<List<UICategory>> = _categoryList

    private val _mealList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> = _mealList

    private val taskEventChannel = Channel<TaskEvent>()
    val taskEvent = taskEventChannel.receiveAsFlow()

    fun getCategoryList() {
        viewModelScope.launch {
            val categoryListFromAPI = menuInteractor.getCategoryList().toUI()
            categoryListFromAPI[0].isChecked = true
            _categoryList.value = categoryListFromAPI
        }
    }

    fun getMealList(category: String) {
        viewModelScope.launch {
            val mealListFromAPI = menuInteractor.getMealList(category)
            taskEventChannel.send(TaskEvent.ShowProgressBar)
            getMealIngredients(mealListFromAPI)
            _mealList.value = mealListFromAPI
            taskEventChannel.send(TaskEvent.HideProgressBar)
        }
    }

    private suspend fun getMealIngredients(mealListFromAPI: List<Meal>) {
        mealListFromAPI.forEach { meal ->
            meal.ingredients = menuInteractor.getMealIngredient(meal.id).description
        }
    }

    sealed class TaskEvent {
        object ShowProgressBar: TaskEvent()
        object HideProgressBar: TaskEvent()
    }
}