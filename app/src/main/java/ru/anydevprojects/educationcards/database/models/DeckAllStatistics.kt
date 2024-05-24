package ru.anydevprojects.educationcards.database.models

data class DeckAllStatistics(
    val id: Long,
    val name: String,
    val numberOfStudies: Int,
    val lastStudyTimestamp: Long
)
