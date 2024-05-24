package ru.anydevprojects.educationcards.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeckStatisticsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "deck_id") val deckId: Long,
    @ColumnInfo(name = "number_of_studies") val numberOfStudies: Int,
    @ColumnInfo(name = "last_study_timestamp") val lastStudyTimestamp: Long

)
