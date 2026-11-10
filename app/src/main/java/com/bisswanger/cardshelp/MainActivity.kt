package com.bisswanger.cardshelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bisswanger.cardshelp.screens.EvaluateHandScreen
import com.bisswanger.cardshelp.screens.ViewPastHandsScreen
import com.bisswanger.cardshelp.ui.theme.CardsHelpTheme
import com.bisswanger.cardshelp.viewModels.evaluateHandViewModel
import com.bisswanger.cardshelp.viewModels.evaluateHandViewModelFactory
import com.bisswanger.cardshelp.viewModels.viewPastHandsViewModel
import com.bisswanger.cardshelp.viewModels.viewPastHandsViewModelFactory
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardsHelpTheme {
                AppNavHost(modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding())

            }
        }
    }
}

@Serializable
object evaluateHandScreen

@Serializable
object viewPastHandsScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val evaluateHandVM: evaluateHandViewModel = viewModel(factory = evaluateHandViewModelFactory())
    val viewPastHandsVM: viewPastHandsViewModel = viewModel(factory = viewPastHandsViewModelFactory())

    NavHost(
        navController = navController,
        startDestination = evaluateHandScreen,
        modifier = modifier
    ) {
        composable<evaluateHandScreen> {
            EvaluateHandScreen(
                viewModel = evaluateHandVM,
                onOpenViewPastHands = { navController.navigate(viewPastHandsScreen) }
            )
        }
        composable<viewPastHandsScreen> {
            ViewPastHandsScreen(
                viewModel = viewPastHandsVM,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
