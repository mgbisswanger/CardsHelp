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

class evaluateHandViewModel(
    private val repository: CardsRepository,
) : ViewModel()
{
    @RequiresApi(Build.VERSION_CODES.O)
    fun addPreviousHand(cards: List<Card>, rank: Int) {
        viewModelScope.launch {
            repository.insertPreviousHand(PreviousHands(LocalDateTime.now().toString(), cards, rank))
        }
    }
}
