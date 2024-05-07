package ru.anydevprojects.educationcards.statistics.decksStatistics.data.mappers

import ru.anydevprojects.educationcards.database.models.DeckAllStatistics
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.models.DeckStatistics

fun DeckAllStatistics.toDomain(): DeckStatistics {
    return DeckStatistics(
        id = this.id,
        deckName = this.name,
        numberOfStudies = this.numberOfStudies,
        lastStudyTimestamp = this.lastStudyTimestamp
    )
}
