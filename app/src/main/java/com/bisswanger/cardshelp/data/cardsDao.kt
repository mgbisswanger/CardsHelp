package com.bisswanger.cardshelp.data

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface CardsDao {

    @Query("SELECT * FROM previousHands")
    fun getAllPreviousHands(): List<PreviousHands>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hand: PreviousHands)

}