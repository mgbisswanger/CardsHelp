package com.bisswanger.cardshelp.screens

import android.R.attr.label
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.geometry.Size
import com.bisswanger.cardshelp.data.Value

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakePictureModal (
    onDismiss: () -> Unit,
    onEvaluate: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        DropdownSuits()
        DropdownValues()
    }
}

@Composable
fun DropdownSuits() {

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mOptions = Suit.entries

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf(Suit.DIAMONDS) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    Column(Modifier.padding(20.dp)) {

        Box (
            modifier = Modifier.clickable {
                mExpanded = !mExpanded
            }
        ) {
            OutlinedTextField(
                value = mSelectedText.name,
                onValueChange = { mSelectedText = Suit.valueOf(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()},
                label = {Text("Suit")},
                readOnly = true
            )
        }


        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label.name) },
                    onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DropdownValues() {

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    val mOptions = Value.entries

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf(Value.ACE) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    Column(Modifier.padding(20.dp)) {

        Box (
            modifier = Modifier.clickable {
                mExpanded = !mExpanded
            }
        ) {
            OutlinedTextField(
                value = mSelectedText.name,
                onValueChange = { mSelectedText = Value.valueOf(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()},
                label = {Text("Value")},
                readOnly = true
            )
        }


        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            mOptions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label.name) },
                    onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }
                )
            }
        }
    }
}