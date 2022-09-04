package com.levox.studyshoppinglist.domain

class GetShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopItemRepository.getShopItem(shopItemId)
    }
}