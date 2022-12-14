package com.levox.studyshoppinglist.domain

class AddShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopItemRepository.addShopItem(shopItem)
    }
}