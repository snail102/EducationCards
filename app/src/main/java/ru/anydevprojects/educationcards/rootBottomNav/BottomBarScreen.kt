package ru.anydevprojects.educationcards.rootBottomNav

import ru.anydevprojects.educationcards.core.navigation.Screens

sealed class BottomBarScreen(val route: String) {
    data object MyDecks : BottomBarScreen(
        route = Screens.MyDecks.route
    )

    data object Profile : BottomBarScreen(
        route = Screens.Profile.route
    )
}
