package com.levox.studyshoppinglist.domain

class EditShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopItemRepository.editShopItem(shopItem)
    }
}