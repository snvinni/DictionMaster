package com.example.dictionmaster.core.ui.component

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DMNavHost(
    navController: NavHostController,
    startDestination: String,
    navHostConfig: List<NavHostConfig>,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navHostConfig.onEach { config ->
            composable(
                route = config.route,
                arguments = config.arguments,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                val wordId = remember {
                    it.arguments?.getInt("id")
                }

                config.screenDestination(wordId)
            }
        }

    }
}

data class NavHostConfig(
    val route: String,
    val screenDestination: @Composable (Int?) -> Unit,
    val arguments: List<NamedNavArgument> = emptyList(),
)