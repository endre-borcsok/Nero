package com.ebsoftware.nero.core.database.stocks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity

@Database(entities = [SecurityMovementEntity::class], version = 1)
internal abstract class StockDatabase : RoomDatabase() {
    abstract fun stockPositionDao(): StockPositionDao
}
