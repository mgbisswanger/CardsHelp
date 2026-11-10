package com.bisswanger.cardshelp.viewModels

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bisswanger.cardshelp.data.CardsHelpApplication

fun evaluateHandViewModelFactory() =
    viewModelFactory {
        initializer {
            evaluateHandViewModel(
                (this[APPLICATION_KEY] as CardsHelpApplication).repository
            )
        }
    }

fun viewPastHandsViewModelFactory() =
    viewModelFactory {
        initializer {
            viewPastHandsViewModel(
                (this[APPLICATION_KEY] as CardsHelpApplication).repository
            )
        }
    }