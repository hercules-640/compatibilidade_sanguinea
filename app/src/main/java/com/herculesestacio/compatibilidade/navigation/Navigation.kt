package com.herculesestacio.compatibilidade.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.herculesestacio.compatibilidade.ui.screens.*

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home   : Screen("home")
    data object History: Screen("history")
    data object About  : Screen("about")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    history: List<QueryResultUi>,
    onAddHistory: (QueryResultUi) -> Unit
) {
    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenHistory = { navController.navigate(Screen.History.route) },
                onOpenAbout   = { navController.navigate(Screen.About.route) },
                onAddHistory  = onAddHistory
            )
        }
        composable(Screen.History.route) {
            HistoryScreen(history = history, onBack = { navController.popBackStack() })
        }
        composable(Screen.About.route) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
    }
}
