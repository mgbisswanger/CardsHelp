package com.bisswanger.cardshelp.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "previousHands")
data class PreviousHands(
    @PrimaryKey val name: String,
    val cardsInHand: List<Card>,
    val rankStatus: Int?
)