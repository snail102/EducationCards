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

    data object StudyCardsNavGraph : Screens {
        override val route: String = "studyCardsNavGraph"
    }

    data object CardsStatisticsNavGraph : Screens {
        override val route: String = "cardsStatisticsNavGraph"
    }

    data object DecksStatisticsNavGraph : Screens {
        override val route: String = "decksStatisticsNavGraph"
    }

    data object CardsStatistics : Screens {
        override val route: String = "cardsStatistics"
    }

    data object DecksStatistics : Screens {
        override val route: String = "decksStatistics"
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

        fun getRouteWithArgs(deckId: Long, deckName: String): String {
            return "deckViewer/$deckId/$deckName"
        }
    }

    data object StudyCards : Screens {
        override val route: String = "studyCards/{deckId}"

        val deckIdArg: String = "deckId"

        fun getRouteWithArgs(deckId: Long): String {
            return "studyCards/$deckId"
        }
    }

    data object CardEditor : Screens {
        override val route: String = "cardEditor/{cardId}"

        val cardIdArg: String = "cardId"

        fun getRouteWithArgs(cardId: Long): String {
            return "cardEditor/$cardId"
        }
    }
}
