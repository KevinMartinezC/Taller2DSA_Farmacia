package com.udb.taller2DSA.network

import com.udb.taller2DSA.database.*
import com.udb.taller2DSA.model.RecordModel
import com.udb.taller2DSA.model.CartModel
import com.udb.taller2DSA.model.ProductFarmacyModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ProductFarmacy(
    val id: String,
    var name: String,
    @Json(name="image") val imgSrcUrl: String,
    val description: String,
    val price: String
)



//type to database
fun List<ProductFarmacy>.asDatabaseFarmacyProduct(): List<DatabaseFarmacy > {
    return map {
        DatabaseFarmacy (
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            description = it.description,
            price = it.price
        )
    }
}


//for database to model(domain)
fun List<DatabaseFarmacy >.asDomainFarmacyProductModel(): List<ProductFarmacyModel> {
    return map {
        ProductFarmacyModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            description = it.description,
            price = it.price
        )
    }
}

//FOR CART::
fun DatabaseFarmacy.asDatabaseCartType(): DatabaseCart{
    return DatabaseCart(
        id = id,
        name = name,
        imgSrcUrl = imgSrcUrl,
        price = price
    )
}
fun List<DatabaseCart>.asDomainCartModel(): List<CartModel>{
    return map {
        CartModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//For Account
fun List<DatabaseAccount>.asDomainAccountModel(): List<RecordModel>{
    return map {
        RecordModel(
            id = it.id,
            price = it.price
        )
    }
}