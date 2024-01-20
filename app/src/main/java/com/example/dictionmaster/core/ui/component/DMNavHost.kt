package com.example.dictionmaster.core.ui.component

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DMNavHost(
    navController: NavHostController,
    starDestination: String,
    navHostConfig: List<NavHostConfig>,
) {
    NavHost(
        navController = navController,
        startDestination = starDestination,
    ) {
        navHostConfig.onEach { config ->
            composable(
                route = config.route,
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
                config.screenDestination()
            }
        }

    }
}

data class NavHostConfig(
    val route: String,
    val screenDestination: @Composable () -> Unit
)