package com.levox.studyshoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.levox.studyshoppinglist.R
import com.levox.studyshoppinglist.databinding.ShopItemDisabledBinding
import com.levox.studyshoppinglist.databinding.ShopItemEnabledBinding
import com.levox.studyshoppinglist.domain.ShopItem

class ShopListAdapter
    : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClick: ((ShopItem) -> Unit)? = null
    var onShopItemLongClick: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shop_item_disabled
            VIEW_TYPE_ENABLED -> R.layout.shop_item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }
        binding.root.setOnLongClickListener {
            onShopItemLongClick?.invoke(shopItem)
            true
        }
        when (binding) {
            is ShopItemEnabledBinding -> {
                binding.shopItem = shopItem
            }
            is ShopItemDisabledBinding -> {
                binding.shopItem = shopItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val current = getItem(position)
        return if (current.enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}