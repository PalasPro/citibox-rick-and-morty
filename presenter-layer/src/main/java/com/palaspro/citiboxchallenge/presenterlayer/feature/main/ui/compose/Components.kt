package com.palaspro.citiboxchallenge.presenterlayer.feature.main.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.palaspro.citiboxchallenge.presenterlayer.feature.home.ui.componse.HomeScreen
import com.palaspro.citiboxchallenge.presenterlayer.feature.match.ui.compose.MatchScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

const val ARG_ID_CHARACTER_KEY = "idCharacter"
const val ARG_ID_CHARACTER_DEFAULT = -1

@ExperimentalFoundationApi
@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, getViewModel())
        }
        composable(
            "match/{$ARG_ID_CHARACTER_KEY}",
            arguments = listOf(navArgument(ARG_ID_CHARACTER_KEY) { type = NavType.IntType })
        ) { backEntry ->
            val idCharacter =
                backEntry.arguments?.getInt(ARG_ID_CHARACTER_KEY, ARG_ID_CHARACTER_DEFAULT)
                    ?: ARG_ID_CHARACTER_DEFAULT
            MatchScreen(
                navController = navController,
                viewModel = getViewModel {
                    parametersOf(idCharacter)
                })
        }
    }
}
