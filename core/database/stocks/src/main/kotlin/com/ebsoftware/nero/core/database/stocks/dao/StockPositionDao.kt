package com.ebsoftware.nero.core.database.stocks.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockPositionDao {

    @Query("SELECT * FROM securitymovemententity")
    fun getAll(): Flow<List<SecurityMovementEntity>>

    @Query("SELECT * FROM securitymovemententity WHERE ticker IN (:ticker)")
    fun getAll(
        ticker: String,
    ): Flow<List<SecurityMovementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        vararg users: SecurityMovementEntity,
    )

    @Delete
    suspend fun delete(
        user: SecurityMovementEntity,
    )
}
