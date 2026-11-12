package com.bisswanger.cardshelp.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bisswanger.cardshelp.data.Card
import com.bisswanger.cardshelp.data.CardsRepository
import com.bisswanger.cardshelp.data.PreviousHands
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bisswanger.cardshelp.data.Suit
import com.bisswanger.cardshelp.data.Value
import com.bisswanger.cardshelp.cardLogic.HandType
import com.bisswanger.cardshelp.cardLogic.evaluate

class evaluateHandViewModel(
    private val repository: CardsRepository,
) : ViewModel()
{
    // allow the card to sustain through both dropdown menus of card selection
    var selectedSuits by mutableStateOf(
        List<Suit?>(5) { null }
    )
        private set

    // we need this one too for the second drop down menu
    var selectedValues by mutableStateOf(
        List<Value?>(5) { null }
    )
        private set

    // stores the hand type returned by evaluate function
    var handResult by mutableStateOf<HandType?>(null)
        private set

    // stores error messages for bad hands
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Function: allow changes to card suit
    fun updateSuit(index: Int, suit: Suit) {
        val updatedSuits = selectedSuits.toMutableList()

        updatedSuits[index] = suit

        selectedSuits = updatedSuits

        // clear hand result on card change
        handResult = null
    }

    // Function: allows changes to card value
    fun updateValue(index: Int, value: Value) {
        val updatedValues = selectedValues.toMutableList()

        updatedValues[index] = value

        selectedValues = updatedValues

        // clear hand result on card change
        handResult = null
    }

    //Function:  clears the drop down menus
    fun clearHand() {

        // clear suits
        selectedSuits = List(5) { null }

        // clear card values
        selectedValues = List(5) { null }

        // clear hand result
        handResult = null

        // clear error messages
        errorMessage = null
    }

    // Function: flexible evaluate function for camera or dropdown menu cards
    // unsure how the camera cards will get evaluated so this should be flexible for that
    @RequiresApi(Build.VERSION_CODES.O)
    fun evaluateHand(cards: List<Card>) {
        // clear previous error messages
        errorMessage = null

        // clear previous result
        handResult = null

        // ensure 5 cards
        if (cards.size != 5) {
            errorMessage = "A hand must contain exactly five cards."
            return
        }

        // ensure no duplicates
        if (cards.distinct().size != 5) {
            errorMessage = "You cannot select the same card twice."
            return
        }

        // evaluate cards
        val result = evaluate(cards)

        // use to display handtype to screen
        handResult = result

        // save the cards and int hand rank to room
        addPreviousHand(cards, result.rank)
    }

    // Function: processes the dropdown menu cards and calls the previous function to evaluate
    @RequiresApi(Build.VERSION_CODES.O)
    fun evaluateSelectedCards() {
        // clear previous error messages
        errorMessage = null

        // clear previous result
        handResult = null

        // empty list to hold card data
        val cards = mutableListOf<Card>()

        // for each card drop down
        for (i in 0 until 5) {

            val suit = selectedSuits[i]
            val value = selectedValues[i]

            // ensure value and suit selected
            if (suit == null || value == null) {
                errorMessage = "All cards must be selected."
                return
            }

            // create applicable cards from menus
            cards.add(Card(suit, value))
        }

        // calls the previous evaluate function
        evaluateHand(cards)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPreviousHand(cards: List<Card>, rank: Int) {
        viewModelScope.launch {
            repository.insertPreviousHand(PreviousHands(
                LocalDateTime.now().toString(),
                cards,
                rank))
        }
    }
}
