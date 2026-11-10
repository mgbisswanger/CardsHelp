package com.bisswanger.cardshelp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bisswanger.cardshelp.viewModels.evaluateHandViewModel

@Composable
fun EvaluateHandScreen(
    modifier: Modifier = Modifier,
    viewModel: evaluateHandViewModel,
    onOpenViewPastHands: () -> Unit
) {
    var showTakePictureModel by remember {mutableStateOf(false)}
    Column(
        modifier = modifier
    ) {
        Row (
            modifier = modifier
        ) {
            Button (
                onClick = { showTakePictureModel = true }
            ) {
                Text("Take Picture")
            }

            Button (
                onClick = onOpenViewPastHands
            ) {
                Text("View Past Hands")
            }
        }
        Text(if (showTakePictureModel) "True" else "False")
        if (showTakePictureModel) {
            TakePictureModal(
                onDismiss = {showTakePictureModel = false},
                onEvaluate = {}
            )
        }
    }
}