package com.example.dictionmaster

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dictionmaster.core.ui.component.DMNavHost
import com.example.dictionmaster.core.ui.component.NavHostConfig
import com.example.dictionmaster.feature.NavigationRoute
import com.example.dictionmaster.feature.search.SearchRoute
import com.example.dictionmaster.feature.wordinfo.WordInfoRoute

@Composable
fun MainRoute() {
    val navController = rememberNavController()

    DMNavHost(
        navController = navController,
        startDestination = "search_screen",
        navHostConfig = listOf(
            NavHostConfig(
                route = "search_screen",
                screenDestination = {
                    SearchRoute { navigationRoute ->
                        when (navigationRoute) {
                            NavigationRoute.NavigateToSubscribe -> {
                                navController.navigate("subscribe_screen") {
                                    popUpTo("search_screen") {
                                        inclusive = true
                                    }
                                }
                            }

                            is NavigationRoute.NavigateToWordInfo -> {
                                navController.navigate(
                                    "word_info_screen/${navigationRoute.id}"
                                ) {
                                    popUpTo("search_screen") {
                                        inclusive = true
                                    }
                                }
                            }

                            NavigationRoute.NavigateToSearch -> Unit
                        }

                    }
                }
            ),
            NavHostConfig(
                route = "word_info_screen/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                ),
                screenDestination = { id ->
                    if (id != null) {
                        WordInfoRoute(
                            wordInfoId = id
                        ) { navigationRoute ->
                            when (navigationRoute) {
                                NavigationRoute.NavigateToSearch -> {
                                    navController.navigate("search_screen")
                                }

                                NavigationRoute.NavigateToSubscribe -> {
                                    navController.navigate("subscribe_screen")
                                }

                                is NavigationRoute.NavigateToWordInfo -> Unit
                            }
                        }
                    }
                }
            ),
            NavHostConfig(
                route = "subscribe_screen",
                screenDestination = {
                    Surface {
                        Text(text = "Subscribe Screen")
                    }
                }
            ),
        )
    )
}