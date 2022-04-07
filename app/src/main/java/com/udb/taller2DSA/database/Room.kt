package com.udb.taller2DSA.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommonDao{


    //For kit type
    @Query("select * from databaseFarmacy ")
    fun getFarmacyProduct(): LiveData<List<DatabaseFarmacy >>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduts( kits: List<DatabaseFarmacy >)

    //For cart
    @Query("select * from databaseFarmacy ")
    fun getFarmacyProductForCart(): MutableList<DatabaseFarmacy >
    @Query("select * from databasecart")
    fun getCartForRecView(): LiveData<List<DatabaseCart>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInCart( kits: DatabaseCart)
    @Query("delete from databasecart where id=:id")
    fun remove(id: Int)
    @Query("delete from databasecart")
    fun deleteAll()

    //For account
    @Query("select * from databasecart")
    fun getCartForAccount(): List<DatabaseCart>
    @Query("select * from databaseaccount")
    fun getAccount(): LiveData<List<DatabaseAccount>>
    @Query("select * from databaseaccount")
    fun getAccountForDebug(): List<DatabaseAccount>
    @Query("insert into databaseaccount values(null,:item)")
    fun insertAccount(item: Int)
}

@Database(entities = [ DatabaseFarmacy ::class, DatabaseCart::class, DatabaseAccount::class], version = 1)
abstract class Databases:RoomDatabase(){
    abstract val commonDao: CommonDao
}

private lateinit var INSTANCE: Databases

//creates database when not initialized
fun getDatabase(context: Context): Databases {
    synchronized(Databases::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                Databases::class.java,
                "databases").build()
        }
    }
    return INSTANCE
}