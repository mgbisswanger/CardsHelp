package com.bisswanger.cardshelp.data

import android.app.Application
import androidx.room3.Room

class CardsHelpApplication : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "cardsHelp.db"
        ).build()
    }

    val repository : CardsRepository by lazy {
        CardsRepository(database.cardsDao())
    }
}