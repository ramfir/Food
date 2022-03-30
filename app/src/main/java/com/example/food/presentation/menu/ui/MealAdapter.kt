package com.example.food.presentation.menu.ui

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.food.R
import com.example.food.databinding.ItemMealBinding
import com.example.food.domain.model.Meal
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun getMealAdapterDelegate() = adapterDelegateViewBinding<Meal, Meal, ItemMealBinding>(
    { layoutInflater, root -> ItemMealBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        with(binding) {
            Glide.with(itemView)
                .load(item.img)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.img_error)
                .into(imageViewMeal)
            textViewMealName.text = item.name
            textViewIngredients.text = item.ingredients
        }
    }
}

fun getMealDiffCallback() = object : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
}
