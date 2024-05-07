package com.ebsoftware.nero.core.database.stocks.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ebsoftware.nero.core.database.stocks.model.PositionEntity

@Dao
interface StockPositionDao {

    @Query("SELECT * FROM positionentity")
    suspend fun getAll(): List<PositionEntity>

    @Query("SELECT * FROM positionentity WHERE ticker IN (:ticker)")
    suspend fun getAll(ticker: String): List<PositionEntity>

    @Insert
    suspend fun insert(vararg users: PositionEntity)

    @Delete
    suspend fun delete(user: PositionEntity)
}