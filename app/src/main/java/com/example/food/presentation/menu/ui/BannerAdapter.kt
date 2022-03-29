package com.example.food.presentation.menu.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.food.R
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

/*
class BannerAdapter(
    private val bannerList: List<Banner>
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: Banner) {
            binding.imageViewBanner.setImageResource(banner.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerList[position])
    }

    override fun getItemCount(): Int = bannerList.size
}*/
