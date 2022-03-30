package com.example.food.presentation.menu.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food.R
import com.example.food.databinding.MenuFragmentBinding
import com.example.food.presentation.menu.mvvm.MenuViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
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
            viewModel.categoryItemClicked(it)
        })
    }

    private val mealAdapter by lazy {
        AsyncListDifferDelegationAdapter(getMealDiffCallback(), getMealAdapterDelegate())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MenuFragmentBinding.bind(view)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        setViews()
        setObservation()
    }

    private fun setViews() {
        with(binding) {
            recyclerViewBanner.adapter = bannerAdapter
            recyclerViewCategory.adapter = categoryAdapter
            recyclerViewMeal.adapter = mealAdapter
            recyclerViewMeal.addItemDecoration(
                DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
            )
        }
    }

    private fun setObservation() {
        with(viewModel) {
            bannerList.observe(viewLifecycleOwner) { bannerAdapter.items = it.toList() }
            categoryList.observe(viewLifecycleOwner) { categoryAdapter.items = it.toList() }
            mealList.observe(viewLifecycleOwner) { mealAdapter.items = it.toList() }
            isDownloading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}