package com.levox.studyshoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.levox.studyshoppinglist.data.ShopItemRepositoryImpl
import com.levox.studyshoppinglist.domain.DeleteShopItemUseCase
import com.levox.studyshoppinglist.domain.EditShopItemUseCase
import com.levox.studyshoppinglist.domain.GetShopListUseCase
import com.levox.studyshoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopItemRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledState(shopItem: ShopItem) {
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) {
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}