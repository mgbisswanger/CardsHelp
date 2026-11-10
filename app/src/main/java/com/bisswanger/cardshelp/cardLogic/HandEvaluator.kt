package com.bisswanger.cardshelp.cardLogic

import com.bisswanger.cardshelp.data.Card
import com.bisswanger.cardshelp.data.Value

fun evaluate(cards: List<Card>): HandType {

    // ensure 5 cards, no duplicates
    validateCards(cards)

    // array storing each card's frequency
    val counts = countRanks(cards)

    // check for flush, returns a bool
    val flush = isFlush(cards)

    // check for straight, also returns a bool
    val straight = isStraight(counts)

    // pair storage variables
    var pairs = 0       // normal pair
    var three = false   // three of a kind
    var four = false    // four of a kind

    // iterate through the frequency array to check for pairs and kinds
    for (count in counts) {
        if (count == 2) {
            pairs++
        }
        // three of a kind
        if (count == 3) {
            three = true
        }
        // four of a kind
        if (count == 4) {
            four = true
        }
    }

    // Return the highest ranking hand by listing them in decreasing order
    if (flush && counts[10] == 1 && counts[11] == 1 &&
        counts[12] == 1 && counts[13] == 1 && counts[14] == 1) {
        return HandType.ROYAL_FLUSH
    }

    if (flush && straight) {
        return HandType.STRAIGHT_FLUSH
    }

    if (four) {
        return HandType.FOUR_OF_A_KIND
    }

    if (three && pairs == 1) {
        return HandType.FULL_HOUSE
    }

    if (flush) {
        return HandType.FLUSH
    }

    if (straight) {
        return HandType.STRAIGHT
    }

    if (three) {
        return HandType.THREE_OF_A_KIND
    }

    if (pairs == 2) {
        return HandType.TWO_PAIR
    }

    if (pairs == 1) {
        return HandType.ONE_PAIR
    }

    return HandType.HIGH_CARD
}

// helper functions
private fun validateCards(cards: List<Card>) {

    // check for only 5 cards
    if (cards.size != 5) {
        throw IllegalArgumentException("Must have 5 cards")
    }

    // check for duplicates by comparison
    for (i in 0 until 5) {
        for (j in i + 1 until 5) {
            if (cards[i] == cards[j]) {
                throw IllegalArgumentException("Duplicate card")
            }
        }
    }
}

private fun countRanks(cards: List<Card>): IntArray {

    // array to store card frequency
    val counts = IntArray(15)

    for (i in cards) {
        // convert the card value to a number
        val rank = getRank(i.value)

        // increase card frequency
        counts[rank]++
    }

    return counts
}

private fun isFlush(cards: List<Card>): Boolean {

    var flush = true

    for (i in cards) {
        // if all suits dont match the first card, false
        if (i.suit != cards[0].suit) {
            flush = false
            return flush
        }
    }

    return flush
}

private fun isStraight(counts: IntArray): Boolean {

    // starting at 2, 3,...9,10
    for (startCard in 2..10) {

        var found = true

        // check the next four cards for sequence
        for (rank in startCard..startCard + 4) {
            if (counts[rank] != 1) {
                found = false
            }
        }

        if (found) {
            return true
        }
    }

    // check for straight with an ace as 1
    if (counts[14] == 1 &&
        counts[2] == 1 &&
        counts[3] == 1 &&
        counts[4] == 1 &&
        counts[5] == 1) {
        return true
    }

    return false
}

// convert the Value enum into an integer rank
private fun getRank(value: Value): Int {

    return when (value) {
        Value.TWO -> 2
        Value.THREE -> 3
        Value.FOUR -> 4
        Value.FIVE -> 5
        Value.SIX -> 6
        Value.SEVEN -> 7
        Value.EIGHT -> 8
        Value.NINE -> 9
        Value.TEN -> 10
        Value.JACK -> 11
        Value.QUEEN -> 12
        Value.KING -> 13
        Value.ACE -> 14
    }
}