package com.ebsoftware.nero.core.database.stocks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import com.ebsoftware.nero.core.database.stocks.model.PositionEntity

@Database(entities = [PositionEntity::class], version = 1)
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockPositionDao(): StockPositionDao
}