package com.example.food.presentation.menu.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food.domain.menu.MenuInteractor
import com.example.food.domain.model.Banner
import com.example.food.domain.model.Meal
import com.example.food.presentation.menu.model.CategoryUI
import com.example.food.presentation.menu.model.toUI
import kotlinx.coroutines.launch

class MenuViewModel(private val menuInteractor: MenuInteractor) : ViewModel() {

    private val _bannerList = MutableLiveData(menuInteractor.getBannerList())
    val bannerList: LiveData<List<Banner>> = _bannerList

    private val _categoryList = MutableLiveData<List<CategoryUI>>()
    val categoryList: LiveData<List<CategoryUI>> = _categoryList

    private val _mealList = MutableLiveData<List<Meal>>()
    val mealList: LiveData<List<Meal>> = _mealList

    private val _isDownloading = MutableLiveData<Boolean>()
    val isDownloading: LiveData<Boolean> = _isDownloading

    init {
        viewModelScope.launch {
            val categories = getCategoryList().toMutableList()
            if (!categories.isEmpty()) categories[0] = categories[0].copy(isChecked = true)
            _categoryList.value = categories
            if (!categories.isEmpty()) getMealList(categories.first { it.isChecked }.name)
        }
    }

    private suspend fun getCategoryList(): List<CategoryUI> = menuInteractor.getCategoryList().toUI()

    private suspend fun getMealList(category: String) {
        _isDownloading.value = true
        val meals = menuInteractor.getMealList(category)
        _mealList.value = meals
        _isDownloading.value = false

    }

    fun categoryItemClicked(item: CategoryUI) {
        categoryList.value?.let {
            val index = it.indexOf(item)
            if (index == -1) return
            _categoryList.value = mutableListOf<CategoryUI>().apply {
                it.forEach { add(it.copy(isChecked = false)) }
                this[index] = item.copy(isChecked = true)
            }
        }
        viewModelScope.launch { getMealList(item.name) }
    }
}