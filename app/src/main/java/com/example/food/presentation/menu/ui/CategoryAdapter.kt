package com.example.food.presentation.menu.ui

import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.presentation.menu.model.CategoryUI
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun getCategoryAdapterDelegate(itemClickListener: (CategoryUI) -> Unit) = adapterDelegateViewBinding<CategoryUI, CategoryUI, ItemCategoryBinding>(
    { layoutInflater, root -> ItemCategoryBinding.inflate(layoutInflater, root, false) }
) {

    binding.textViewCategory.setOnClickListener { itemClickListener.invoke(item) }

    bind {
        binding.textViewCategory.text = item.name
        if (item.isChecked) {
            binding.textViewCategory.setTextColor(Color.parseColor("#FD3A69"))
            binding.textViewCategory.setBackgroundColor(Color.parseColor("#feb3c5"))
        } else {
            binding.textViewCategory.setTextColor(Color.GRAY)
            binding.textViewCategory.setBackgroundColor(Color.WHITE)
        }
    }
}

fun getCategoryDiffCallback() = object : DiffUtil.ItemCallback<CategoryUI>() {
    override fun areItemsTheSame(oldItem: CategoryUI, newItem: CategoryUI) = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: CategoryUI, newItem: CategoryUI) = oldItem == newItem
}
