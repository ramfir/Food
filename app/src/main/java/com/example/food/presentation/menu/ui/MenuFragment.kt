package com.example.food.presentation.menu.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.databinding.MenuFragmentBinding
import com.example.food.presentation.menu.model.UICategory
import com.example.food.presentation.menu.mvvm.MenuViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.menu_fragment) {

    private var _binding: MenuFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModel()

    private val bannerAdapter by lazy {
        AsyncListDifferDelegationAdapter(getBannerDiffCallback(), getBannerAdapterDelegate())
    }
    private val categoryAdapter by lazy {
        AsyncListDifferDelegationAdapter(getCategoryDiffCallback(), getCategoryAdapterDelegate() {
            itemClicked(it)
        })
    }

    private fun itemClicked(it: UICategory) {
        categoryAdapter.items.forEach { it.isChecked = false }
        it.isChecked = true
        categoryAdapter.notifyDataSetChanged()
        viewModel.getMealList(it.name)
        mealAdapter.items = listOf()
    }

    private val mealAdapter by lazy {
        AsyncListDifferDelegationAdapter(getMealDiffCallback(), getMealAdapterDelegate())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MenuFragmentBinding.bind(view)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.taskEvent.collect { event ->
                when (event) {
                    is MenuViewModel.TaskEvent.ShowProgressBar ->
                        binding.progressBar.visibility = View.VISIBLE
                    is MenuViewModel.TaskEvent.HideProgressBar ->
                        binding.progressBar.visibility = View.GONE
                }
            }
        }

        setupViews()
        getMenu()
    }

    private fun setupViews() {
        with(binding) {
            recyclerViewBanner.adapter = bannerAdapter
            recyclerViewCategory.adapter = categoryAdapter
            recyclerViewMeal.adapter = mealAdapter
            recyclerViewMeal.addItemDecoration(
                DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
            )
        }
    }

    private fun getMenu() {
        with(viewModel) {
            getCategoryList()
            getMealList("Beef")

            bannerList.observe(viewLifecycleOwner) {
                bannerAdapter.items = it
            }
            categoryList.observe(viewLifecycleOwner) {
                categoryAdapter.items = it
            }
            mealList.observe(viewLifecycleOwner) {
                mealAdapter.items = it
            }
        }
    }
}