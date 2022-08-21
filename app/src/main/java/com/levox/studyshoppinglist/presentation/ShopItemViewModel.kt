package com.levox.studyshoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.levox.studyshoppinglist.data.ShopItemRepositoryImpl
import com.levox.studyshoppinglist.domain.*

class ShopItemViewModel : ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    var id: Int? = null
    val shopItem = repository.getShopItem(id!!)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValidated = isInputValid(name, count)
        if (inputValidated) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val inputValidated = isInputValid(name, count)
        if (inputValidated) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun isInputValid(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            //TODO: show name input error
            result = false
        }
        if (count <= 0) {
            //TODO: show count input error
            result = false
        }
        return result
    }
}