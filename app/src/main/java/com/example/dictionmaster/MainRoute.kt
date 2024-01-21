package com.example.dictionmaster

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dictionmaster.core.ui.component.DMNavHost
import com.example.dictionmaster.core.ui.component.NavHostConfig
import com.example.dictionmaster.feature.search.SearchRoute
import com.example.dictionmaster.feature.wordinfo.WordInfoRoute

@Composable
fun MainRoute(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()

    DMNavHost(
        navController = navController,
        startDestination = "search_screen",
        navHostConfig = listOf(
            NavHostConfig(
                route = "search_screen",
                screenDestination = {
                    SearchRoute(
                        navController = navController,
                        onNavigateToWordInfo = { word ->
                            navController.navigate("word_info_screen/$word") {
                                popUpTo("search_screen") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            ),

            NavHostConfig(
                route = "word_info_screen/{word}",
                arguments = listOf(
                    navArgument("word") {
                        type = NavType.StringType
                    }
                ),
                screenDestination = { word ->
                    if (word != null) {
                        WordInfoRoute(
                            wordInfo = word
                        )
                    }
                }
            )
        )
    )
}