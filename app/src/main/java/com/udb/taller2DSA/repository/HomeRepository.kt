package com.udb.taller2DSA.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udb.taller2DSA.database.Databases
import com.udb.taller2DSA.model.RecordModel
import com.udb.taller2DSA.model.CartModel
import com.udb.taller2DSA.model.ProductFarmacyModel
import com.udb.taller2DSA.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {

    val cartItemsForRecView: LiveData<List<CartModel>> =
        Transformations.map(database.commonDao.getCartForRecView()) { it.asDomainCartModel() }
    val orders: LiveData<List<RecordModel>> =
        Transformations.map(database.commonDao.getAccount()) { it.asDomainAccountModel() }
    val products: LiveData<List<ProductFarmacyModel>> =
        Transformations.map(database.commonDao.getFarmacyProduct()) { it.asDomainFarmacyProductModel() }


    suspend fun refreshProductsAndItems() {
        withContext(Dispatchers.IO) {
            val farmacyProducts = Api.retrofitService.getFarmacyProductsAsync().await()

            with(database.commonDao) {
                insertAllProduts(farmacyProducts.asDatabaseFarmacyProduct())

            }
        }
    }

    suspend fun add(id: Int){
        withContext(Dispatchers.IO) {
            val ProductsForCart = database.commonDao.getFarmacyProductForCart()
            database.commonDao.insertInCart(ProductsForCart[id-1].asDatabaseCartType())
        }
    }

    suspend fun remove(id: Int) {
        withContext(Dispatchers.IO) {
            database.commonDao.remove(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            val listCart = database.commonDao.getCartForAccount()
            val price = listCart.sumBy { it.price.substring(1).toInt() }
            database.commonDao.insertAccount(price)
            delay(2200)
            database.commonDao.deleteAll()
        }
    }
}