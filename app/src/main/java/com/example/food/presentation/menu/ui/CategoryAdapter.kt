package com.example.food.presentation.menu.ui

import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.presentation.menu.model.UICategory
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun getCategoryAdapterDelegate(itemClickListener: (UICategory) -> Unit) = adapterDelegateViewBinding<UICategory, UICategory, ItemCategoryBinding>(
    { layoutInflater, root -> ItemCategoryBinding.inflate(layoutInflater, root, false) }
) {

    binding.textViewCategory.setOnClickListener {
        itemClickListener(item)
    }

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

fun getCategoryDiffCallback() = object : DiffUtil.ItemCallback<UICategory>() {
    override fun areItemsTheSame(oldItem: UICategory, newItem: UICategory) = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: UICategory, newItem: UICategory) = oldItem == newItem
}
