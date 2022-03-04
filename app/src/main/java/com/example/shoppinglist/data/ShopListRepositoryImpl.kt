package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl:ShopListRepository {

    private val shopListLiveDate = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0;

    init {
        for (i in  0 until 10) {
        val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {

        if (shopItem.id == ShopItem.UNDEFINDER_ID) {
            shopItem.id = autoIncrementId ++
        }

        shopList.add(shopItem)
        updaleList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updaleList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        deleteShopItem(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id==shopItemId
        }?:throw RuntimeException("Элемент с id $shopItemId не найден")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
       return shopListLiveDate
    }

    private fun updaleList(){
        shopListLiveDate.value = shopList.toList()
    }
}