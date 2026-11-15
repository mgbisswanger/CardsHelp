package com.bisswanger.cardshelp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bisswanger.cardshelp.data.PreviousHands
import com.bisswanger.cardshelp.viewModels.viewPastHandsViewModel
import com.bisswanger.cardshelp.cardLogic.evaluate

@Composable
fun ViewPastHandsScreen(
    modifier: Modifier = Modifier,
    viewModel: viewPastHandsViewModel,
    onBack: () -> Unit
) {
    val hands by viewModel.hands

    LaunchedEffect(Unit) {
        viewModel.refreshHands()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBack) {
                Text("Back")
            }

            Text(
                text = "Past Hands",
                modifier = Modifier.padding(start = 12.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Text(
            text = "${hands.size} saved hands",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        if (hands.isEmpty()) {
            Text("No saved hands yet")
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(hands.sortedByDescending { it.name }) { hand ->
                    PastHand(hand)
                }
            }
        }
    }
}

@Composable
fun PastHand(
    hand: PreviousHands,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Saved Hand",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = hand.name.substringBefore('.').replace('T', ' ')
            )

            Text(
                text = "Cards (${hand.cardsInHand.size}):",
                modifier = Modifier.padding(top = 8.dp)
            )

            if (hand.cardsInHand.isEmpty()) {
                Text("No cards recorded")
            } else {
                Text(
                    text = hand.cardsInHand.joinToString(" | ") { card ->
                        "${card.value} of ${card.suit}"
                    }
                )
            }
            val handType = try {
                evaluate(hand.cardsInHand).name.replace("_", " ")
            } catch (e: IllegalArgumentException) {
                "Unknown Hand"
            }

            Text(
                text = "Hand: $handType",
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "Rank: ${hand.rankStatus ?: "Not evaluated"}",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
