package com.bisswanger.cardshelp.data

class CardsRepository (
    private val cardsDao: CardsDao
) {
    suspend fun getAllPreviousHands() : List<PreviousHands> {
        return cardsDao.getAllPreviousHands()
    }

    suspend fun insertPreviousHand(hand: PreviousHands) {
        cardsDao.insert(hand)
    }
}