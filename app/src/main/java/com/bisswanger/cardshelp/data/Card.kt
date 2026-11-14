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
) {
    companion object {
        // turns a label from Gemini like "10H" into a card
        fun fromLabel(label: String): Card? {
            // every valid label has at least a rank a suit
            if (label.length < 2) {
                return null
            }

            // the suit is always the last character
            val suit = when (label.last()) {
                'C' -> Suit.CLUBS
                'D' -> Suit.DIAMONDS
                'H' -> Suit.HEARTS
                'S' -> Suit.SPADES
                else -> return null
            }

            // the rank is everything before the last suit
            val value = when (label.dropLast(1)) {
                "2" -> Value.TWO
                "3" -> Value.THREE
                "4" -> Value.FOUR
                "5" -> Value.FIVE
                "6" -> Value.SIX
                "7" -> Value.SEVEN
                "8" -> Value.EIGHT
                "9" -> Value.NINE
                "10" -> Value.TEN
                "J" -> Value.JACK
                "K" -> Value.KING
                "Q" -> Value.QUEEN
                "A" -> Value.ACE
                else -> return null
            }

            return Card(suit, value)
        }
    }
}
