package com.bisswanger.cardshelp.data

import kotlinx.serialization.Serializable

enum class Suit {
    HEARTS, DIAMONDS, SPADES, CLUBS
}

enum class Value {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

@Serializable
data class Card(
    val suit: Suit,
    val value: Value
)