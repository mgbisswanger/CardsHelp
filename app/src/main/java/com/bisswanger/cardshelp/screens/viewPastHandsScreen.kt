package com.bisswanger.cardshelp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bisswanger.cardshelp.data.PreviousHands
import com.bisswanger.cardshelp.viewModels.evaluateHandViewModel
import com.bisswanger.cardshelp.viewModels.viewPastHandsViewModel

@Composable
fun ViewPastHandsScreen(
    modifier: Modifier = Modifier,
    viewModel: viewPastHandsViewModel,
    onBack: () -> Unit
) {
    val hands by viewModel.hands
    Column(
        modifier = modifier
    ) {
        Row (
            modifier = modifier
        ) {
            Button (
                onClick = onBack
            ) {
                Text("Back")
            }
        }
        LazyColumn {
            items(hands) { hand ->
                PastHand(hand, modifier)
            }
        }
    }
}

@Composable
fun PastHand (
    hand: PreviousHands,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(hand.name)
        LazyColumn {
            items(hand.cardsInHand) { card ->
                Text("${card.value} of ${card.suit}")
            }
        }
        Text("Rank: ${hand.rankStatus}")
    }
}