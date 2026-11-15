package com.bisswanger.cardshelp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.bisswanger.cardshelp.data.Suit
import com.bisswanger.cardshelp.viewModels.evaluateHandViewModel
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.bisswanger.cardshelp.data.Value

@Composable
@RequiresApi(Build.VERSION_CODES.O)
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
        // give each card a unique entry, 5 total
        for (i in 0 until 5) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), // add space next to card name
                verticalAlignment = Alignment.CenterVertically
            ) {

                // label the cards 1 to 5
                Text(
                    text = "Card ${i + 1}",
                    modifier = Modifier.width(65.dp)
                )

                // suit dropdown for cards
                Box(modifier = Modifier.weight(1f)) {
                    DropdownSuits(
                        selectedSuit = viewModel.selectedSuits[i],
                        onSuitSelected = { suit ->
                            viewModel.updateSuit(i, suit) // updates suit
                        }
                    )
                }

                // value dropdown for cards
                Box(modifier = Modifier.weight(1f)) {
                    DropdownValues(
                        selectedValue = viewModel.selectedValues[i],
                        onValueSelected = { value ->
                            viewModel.updateValue(i, value) // updates value
                        }
                    )
                }
            }
        }
        // center evaluate and clear buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            // evaluate cards from the drop down menu
            Button(
                onClick = {
                    viewModel.evaluateSelectedCards()
                }
            ) {
                Text("Evaluate")
            }

            // space between buttons
            Spacer(modifier = Modifier.width(12.dp))

            // clear drop down menus, result and error messages
            OutlinedButton(
                onClick = {
                    viewModel.clearHand()
                }
            ) {
                Text("Clear")
            }
        }

        // center the evaluation result
        Text(
            text = viewModel.handResult?.displayName ?: "No hand evaluated",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        // error message section
        if (viewModel.errorMessage != null) {
            Text(
                text = viewModel.errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}

// Function: displays the available suits when expanded
@Composable
fun DropdownSuits(
    selectedSuit: Suit?,
    onSuitSelected: (Suit) -> Unit
) {

    // track if the menu is open
    var mExpanded by remember { mutableStateOf(false) }

    // store the suits
    val mOptions = Suit.entries

    // stores text field size
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(Modifier.padding(20.dp)) {

        Box {
            OutlinedTextField(
                // display suit name
                value = selectedSuit?.name ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Suit") },
                readOnly = true
            )

            // added to enable clicks to register the dropdown
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        mExpanded = true
                    }
            )
        }

        // actual dropdown menu
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            // keeps drop menu the same width as text box
            modifier = Modifier.width(
                with(LocalDensity.current) {
                    mTextFieldSize.width.toDp()
                }
            )
        ) {
            mOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label.name) },
                    onClick = {
                        // what suit was selected
                        onSuitSelected(label)

                        // close drop down
                        mExpanded = false
                    }
                )
            }
        }
    }
}

// Function: displays the available rank values when expanded
@Composable
fun DropdownValues(
    selectedValue: Value?,
    onValueSelected: (Value) -> Unit
) {
    // track if menu is open
    var mExpanded by remember { mutableStateOf(false) }

    // store card values
    val mOptions = Value.entries


    // store text field size
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(Modifier.padding(20.dp)) {

        Box {
            OutlinedTextField(
                // display card value
                value = selectedValue?.name ?: "",
                onValueChange = {},

                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },

                label = { Text("Value") },
                readOnly = true
            )

            // added to enable drop down functionality
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        mExpanded = true
                    }
            )
        }

        // actual menu for card values
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            // makes the dropdown menu the same width as the text field
            modifier = Modifier.width(
                with(LocalDensity.current) {
                    mTextFieldSize.width.toDp()
                }
            )
        ) {
            mOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label.name) },
                    onClick = {
                        // what card value was selected
                        onValueSelected(label)

                        // close drop down
                        mExpanded = false
                    }
                )
            }
        }
    }
}