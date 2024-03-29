package ru.anydevprojects.educationcards.core.navigation

sealed interface Screens {
    val route: String

    data object BottomNavGraph : Screens {
        override val route: String = "bottomNavGraph"
    }

    data object DeckViewerNavGraph : Screens {
        override val route: String = "deckViewerNavGraph"
    }

    data object CardEditorNavGraph : Screens {
        override val route: String = "cardEditorNavGraph"
    }

    data object Profile : Screens {
        override val route: String = "profile"
    }

    data object MyDecks : Screens {
        override val route: String = "myDecks"
    }

    data object DeckViewer : Screens {
        override val route: String = "deckViewer/{deckId}/{deckName}"

        val deckIdArg: String = "deckId"
        val deckNameArg: String = "deckName"

        fun getRouteWithArgs(deckId: String, deckName: String): String {
            return "deckViewer/$deckId/$deckName"
        }
    }

    data object CardEditor : Screens {
        override val route: String = "cardEditor/{cardId}"

        val cardIdArg: String = "cardId"

        fun getRouteWithArgs(cardId: String): String {
            return "cardEditor/$cardId"
        }
    }
}
