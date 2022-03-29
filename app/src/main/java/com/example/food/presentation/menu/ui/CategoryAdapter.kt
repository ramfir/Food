package com.example.food.presentation.menu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.ItemCategoryBinding
import com.example.food.domain.model.Category

class CategoryAdapter(): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList = mutableListOf<Category>()

    class CategoryViewHolder(private val binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.textViewCategory.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int  = categoryList.size

    fun setData(categoryList: List<Category>) {
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }
}