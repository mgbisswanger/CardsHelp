package com.bisswanger.cardshelp

import com.bisswanger.cardshelp.cardLogic.evaluate
import com.bisswanger.cardshelp.cardLogic.HandType
import com.bisswanger.cardshelp.data.Card
import com.bisswanger.cardshelp.data.Suit
import com.bisswanger.cardshelp.data.Value

import org.junit.Test
import org.junit.Assert.assertEquals

class HandEvaluatorTest {

    // test poker hands

    @Test
    fun testRoyalFlush() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TEN),
            Card(Suit.HEARTS, Value.JACK),
            Card(Suit.HEARTS, Value.QUEEN),
            Card(Suit.HEARTS, Value.KING),
            Card(Suit.HEARTS, Value.ACE)
        )

        assertEquals(HandType.ROYAL_FLUSH, evaluate(cards))
    }

    @Test
    fun testStraightFlush() {
        val cards = listOf(
            Card(Suit.SPADES, Value.FIVE),
            Card(Suit.SPADES, Value.SIX),
            Card(Suit.SPADES, Value.SEVEN),
            Card(Suit.SPADES, Value.EIGHT),
            Card(Suit.SPADES, Value.NINE)
        )

        assertEquals(HandType.STRAIGHT_FLUSH, evaluate(cards))
    }

    @Test
    fun testFourOfAKind() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.KING),
            Card(Suit.DIAMONDS, Value.KING),
            Card(Suit.CLUBS, Value.KING),
            Card(Suit.SPADES, Value.KING),
            Card(Suit.HEARTS, Value.TWO)
        )

        assertEquals(HandType.FOUR_OF_A_KIND, evaluate(cards))
    }

    @Test
    fun testFullHouse() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.KING),
            Card(Suit.SPADES, Value.KING),
            Card(Suit.DIAMONDS, Value.KING),
            Card(Suit.CLUBS, Value.EIGHT),
            Card(Suit.HEARTS, Value.EIGHT)
        )

        assertEquals(HandType.FULL_HOUSE, evaluate(cards))
    }

    @Test
    fun testFlush() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TWO),
            Card(Suit.HEARTS, Value.FIVE),
            Card(Suit.HEARTS, Value.EIGHT),
            Card(Suit.HEARTS, Value.JACK),
            Card(Suit.HEARTS, Value.KING)
        )

        assertEquals(HandType.FLUSH, evaluate(cards))
    }

    @Test
    fun testStraight() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.FIVE),
            Card(Suit.CLUBS, Value.SIX),
            Card(Suit.SPADES, Value.SEVEN),
            Card(Suit.DIAMONDS, Value.EIGHT),
            Card(Suit.HEARTS, Value.NINE)
        )

        assertEquals(HandType.STRAIGHT, evaluate(cards))
    }

    @Test
    fun testThreeOfAKind() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.QUEEN),
            Card(Suit.SPADES, Value.QUEEN),
            Card(Suit.DIAMONDS, Value.QUEEN),
            Card(Suit.CLUBS, Value.FIVE),
            Card(Suit.HEARTS, Value.NINE)
        )

        assertEquals(HandType.THREE_OF_A_KIND, evaluate(cards))
    }

    @Test
    fun testTwoPair() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.JACK),
            Card(Suit.SPADES, Value.JACK),
            Card(Suit.DIAMONDS, Value.FOUR),
            Card(Suit.CLUBS, Value.FOUR),
            Card(Suit.HEARTS, Value.NINE)
        )

        assertEquals(HandType.TWO_PAIR, evaluate(cards))
    }

    @Test
    fun testOnePair() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.EIGHT),
            Card(Suit.SPADES, Value.EIGHT),
            Card(Suit.DIAMONDS, Value.THREE),
            Card(Suit.CLUBS, Value.FIVE),
            Card(Suit.HEARTS, Value.KING)
        )

        assertEquals(HandType.ONE_PAIR, evaluate(cards))
    }

    @Test
    fun testHighCard() {
        val cards = listOf(
            Card(Suit.SPADES, Value.ACE),
            Card(Suit.HEARTS, Value.JACK),
            Card(Suit.DIAMONDS, Value.NINE),
            Card(Suit.CLUBS, Value.SIX),
            Card(Suit.HEARTS, Value.TWO)
        )

        assertEquals(HandType.HIGH_CARD, evaluate(cards))
    }

    // SPECIAL CASES

    @Test
    fun testAceLowStraight() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.ACE),
            Card(Suit.CLUBS, Value.TWO),
            Card(Suit.SPADES, Value.THREE),
            Card(Suit.DIAMONDS, Value.FOUR),
            Card(Suit.HEARTS, Value.FIVE)
        )

        assertEquals(HandType.STRAIGHT, evaluate(cards))
    }

    @Test
    fun testAceLowStraightFlush() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.ACE),
            Card(Suit.HEARTS, Value.TWO),
            Card(Suit.HEARTS, Value.THREE),
            Card(Suit.HEARTS, Value.FOUR),
            Card(Suit.HEARTS, Value.FIVE)
        )

        assertEquals(HandType.STRAIGHT_FLUSH, evaluate(cards))
    }

    @Test
    fun testAceHighStraight() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TEN),
            Card(Suit.CLUBS, Value.JACK),
            Card(Suit.SPADES, Value.QUEEN),
            Card(Suit.DIAMONDS, Value.KING),
            Card(Suit.HEARTS, Value.ACE)
        )

        assertEquals(HandType.STRAIGHT, evaluate(cards))
    }

    @Test
    fun testMiddleAce() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.QUEEN),
            Card(Suit.CLUBS, Value.KING),
            Card(Suit.SPADES, Value.ACE),
            Card(Suit.DIAMONDS, Value.TWO),
            Card(Suit.HEARTS, Value.THREE)
        )

        assertEquals(HandType.HIGH_CARD, evaluate(cards))
    }

    @Test
    fun testUnorderedStraight() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.NINE),
            Card(Suit.CLUBS, Value.FIVE),
            Card(Suit.SPADES, Value.SEVEN),
            Card(Suit.DIAMONDS, Value.SIX),
            Card(Suit.HEARTS, Value.EIGHT)
        )

        assertEquals(HandType.STRAIGHT, evaluate(cards))
    }

    @Test
    fun testFlushNotStraightFlush() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TWO),
            Card(Suit.HEARTS, Value.FIVE),
            Card(Suit.HEARTS, Value.EIGHT),
            Card(Suit.HEARTS, Value.JACK),
            Card(Suit.HEARTS, Value.KING)
        )

        assertEquals(HandType.FLUSH, evaluate(cards))
    }

    // validity tests

    @Test(expected = IllegalArgumentException::class)
    fun testFourCards() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TWO),
            Card(Suit.CLUBS, Value.THREE),
            Card(Suit.SPADES, Value.FOUR),
            Card(Suit.DIAMONDS, Value.FIVE)
        )

        evaluate(cards)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSixCards() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.TWO),
            Card(Suit.CLUBS, Value.THREE),
            Card(Suit.SPADES, Value.FOUR),
            Card(Suit.DIAMONDS, Value.FIVE),
            Card(Suit.HEARTS, Value.SIX),
            Card(Suit.CLUBS, Value.SEVEN)
        )

        evaluate(cards)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDuplicateCard() {
        val cards = listOf(
            Card(Suit.HEARTS, Value.KING),
            Card(Suit.HEARTS, Value.KING),
            Card(Suit.CLUBS, Value.EIGHT),
            Card(Suit.DIAMONDS, Value.FIVE),
            Card(Suit.SPADES, Value.TWO)
        )

        evaluate(cards)
    }
}

