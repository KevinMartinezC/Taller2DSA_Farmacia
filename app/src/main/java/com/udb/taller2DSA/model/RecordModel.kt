package com.udb.taller2DSA.model

data class RecordModel (
    val id: Int,
    val price: Int
){
    val idText = "Compra #$id"
    val priceText = "$$price"
}