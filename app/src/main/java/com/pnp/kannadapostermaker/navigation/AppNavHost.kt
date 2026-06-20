package com.pnp.kannadapostermaker.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pnp.kannadapostermaker.presentation.EditorScreen
import com.pnp.kannadapostermaker.presentation.HomeScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    Scaffold { paddingValues ->

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(Screen.Home.route) {

                HomeScreen(
                    onNavigateToEditor = {
                        navController.navigate(
                            Screen.Editor.route
                        )
                    }
                )
            }

            composable(Screen.Editor.route) {

                EditorScreen()
            }
        }
    }
}