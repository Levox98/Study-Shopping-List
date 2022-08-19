package com.levox.studyshoppinglist.data

import com.levox.studyshoppinglist.domain.ShopItem
import com.levox.studyshoppinglist.domain.ShopItemRepository
import java.lang.RuntimeException

object ShopItemRepositoryImpl : ShopItemRepository {

    private val shopItemList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {

        for (i in 0 until 10) {
            addShopItem(ShopItem("$i", i, true))
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        deleteShopItem(oldItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopItemList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Item with id: $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopItemList.toList()
    }
}