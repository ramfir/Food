package com.example.food.presentation.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.R
import com.example.food.databinding.MenuFragmentBinding
import com.example.food.presentation.menu.mvvm.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.menu_fragment) {

    val viewModel: MenuViewModel by viewModel()
    //private var categoryAdapter = CategoryAdapter(listOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MenuFragmentBinding.bind(view)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewModel.getCategoryList()
        viewModel.getMealList("Beef")

        binding.recyclerViewMeal.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

        binding.recyclerViewCategory.adapter = CategoryAdapter()
        binding.recyclerViewMeal.adapter = MealAdapter(listOf())

        viewModel.bannerList.observe(viewLifecycleOwner) {
            binding.recyclerViewBanner.adapter = BannerAdapter(it)
        }
        viewModel.categoryList.observe(viewLifecycleOwner) {
            (binding.recyclerViewCategory.adapter as CategoryAdapter).setData(it)
        }
        viewModel.mealList.observe(viewLifecycleOwner) {
            binding.recyclerViewMeal.adapter = MealAdapter(it)
        }
    }
}