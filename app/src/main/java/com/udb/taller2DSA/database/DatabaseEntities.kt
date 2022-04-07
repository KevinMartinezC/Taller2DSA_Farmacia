package com.udb.taller2DSA.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entities

//For FarmacyProducts
@Entity
data class DatabaseFarmacy constructor(
    @PrimaryKey
    val id: String,
    var name: String,
    val imgSrcUrl: String,
    val description: String,
    val price: String)


//For cart
@Entity
data class DatabaseCart constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String,
    val price: String)

//For Account
@Entity
data class DatabaseAccount constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val price: Int)