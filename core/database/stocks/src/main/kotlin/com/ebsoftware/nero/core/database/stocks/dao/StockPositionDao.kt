package com.ebsoftware.nero.core.database.stocks.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ebsoftware.nero.core.database.stocks.model.PositionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockPositionDao {

    @Query("SELECT * FROM positionentity")
    fun getAll(): Flow<List<PositionEntity>>

    @Query("SELECT * FROM positionentity WHERE ticker IN (:ticker)")
    fun getAll(ticker: String): Flow<List<PositionEntity>>

    @Insert
    suspend fun insert(vararg users: PositionEntity)

    @Delete
    suspend fun delete(user: PositionEntity)
}