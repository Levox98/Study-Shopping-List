package com.levox.studyshoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.levox.studyshoppinglist.data.ShopItemRepositoryImpl
import com.levox.studyshoppinglist.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}