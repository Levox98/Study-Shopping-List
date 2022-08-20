package com.levox.studyshoppinglist.domain

class DeleteShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopItemRepository.deleteShopItem(shopItem)
    }
}