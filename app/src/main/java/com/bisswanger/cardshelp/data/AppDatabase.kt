package com.bisswanger.cardshelp.data

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(entities = [PreviousHands::class], version = 1)
@ColumnTypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardsDao():
            CardsDao
}