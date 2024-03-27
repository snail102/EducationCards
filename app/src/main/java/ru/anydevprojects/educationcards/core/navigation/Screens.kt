package ru.anydevprojects.educationcards.core.navigation

sealed interface Screens {
    val route: String

    data object BottomNavGraph : Screens {
        override val route: String = "bottomNavGraph"
    }

    data object DeckViewerNavGraph : Screens {
        override val route: String = "beckViewerNavGraph"
    }

    data object Profile : Screens {
        override val route: String = "profile"
    }

    data object MyDecks : Screens {
        override val route: String = "myDecks"
    }

    data object DeckViewer : Screens {
        override val route: String = "deckViewer/{deckId}"

        val deckIdArg: String = "deckId"

        fun getRouteWithArgs(deckId: String): String {
            return "deckViewer/$deckId"
        }
    }
}
