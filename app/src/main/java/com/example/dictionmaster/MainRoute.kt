package com.example.dictionmaster

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.dictionmaster.core.ui.component.DMNavHost
import com.example.dictionmaster.core.ui.component.NavHostConfig
import com.example.dictionmaster.feature.search.SearchScreen

@Composable
fun MainRoute(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()

    DMNavHost(
        navController = navController,
        starDestination = "search_screen",
        navHostConfig = listOf(
            NavHostConfig(
                route = "search_screen",
                screenDestination = {
                    SearchScreen(
                        onClick = {
                            navController.navigate("test") {
                                popUpTo("search_screen") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            ),
        )
    )
}