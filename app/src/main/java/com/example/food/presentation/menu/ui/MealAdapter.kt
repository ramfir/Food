package com.example.food.presentation.menu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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

/*
class MealAdapter(
    private val mealList: List<Meal>
): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    class MealViewHolder(private val binding: ItemMealBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            binding.apply {
                Glide.with(itemView)
                    .load(meal.img)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.img_error)
                    .into(imageViewMeal)
                textViewMealName.text = meal.name
                textViewIngredients.text = meal.ingredients
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int  = mealList.size
}*/
