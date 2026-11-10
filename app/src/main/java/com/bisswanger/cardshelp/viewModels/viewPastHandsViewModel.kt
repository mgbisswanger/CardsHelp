package com.bisswanger.cardshelp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bisswanger.cardshelp.data.CardsRepository
import com.bisswanger.cardshelp.data.PreviousHands
import kotlinx.coroutines.launch

class viewPastHandsViewModel(
    private val repository: CardsRepository,
) : ViewModel()
{
    private val _hands = mutableStateOf<List<PreviousHands>>(emptyList())
    val hands : State<List<PreviousHands>> = _hands

    private fun loadPastHands() {
        viewModelScope.launch {
            try {
                _hands.value = repository.getAllPreviousHands()
            }
            catch(e: Exception) {
                println("Failed to fetch previous hands")
            }
        }
    }

    init {
        loadPastHands()
    }
}