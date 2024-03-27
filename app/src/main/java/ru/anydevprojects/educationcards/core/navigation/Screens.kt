package ru.anydevprojects.educationcards.core.navigation

sealed interface Screens {
    val route: String

    data object BottomNavGraph : Screens {
        override val route: String = "BottomNavGraph"
    }

    data object DeckViewerNavGraph : Screens {
        override val route: String = "DeckViewerNavGraph"
    }

    data object Profile : Screens {
        override val route: String = "Profile"
    }

    data object MyDecks : Screens {
        override val route: String = "MyDecks"
    }

    data object DeckViewer : Screens {
        override val route: String = "DeckViewer"
    }
}
