package com.example.shoppinglist.domain

class GetShopLIstUseCase (private val shopListRepository: ShopListRepository) {

    fun getShopList():List<ShopItem>{
        return shopListRepository.getShopList()
    }
}