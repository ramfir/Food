package com.example.food.presentation.menu.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.food.databinding.ItemBannerBinding
import com.example.food.domain.model.Banner
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun getBannerAdapterDelegate() = adapterDelegateViewBinding<Banner, Banner, ItemBannerBinding>(
    { layoutInflater, root -> ItemBannerBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.imageViewBanner.setImageResource(item.img)
    }
}

fun getBannerDiffCallback() = object : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner) = oldItem.img == newItem.img
    override fun areContentsTheSame(oldItem: Banner, newItem: Banner) = oldItem == newItem
}
