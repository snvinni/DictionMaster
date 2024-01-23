package com.example.dictionmaster.feature

sealed interface NavigationRoute {
    data object NavigateToSearch : NavigationRoute

    data object NavigateToSubscribe : NavigationRoute

    data class NavigateToWordInfo(val id: Int) : NavigationRoute
}