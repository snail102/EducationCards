package ru.anydevprojects.educationcards.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardStatisticsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "card_id") val cardId: Long,
    @ColumnInfo(name = "number_of_studies") val numberOfStudies: Int,
    @ColumnInfo(name = "last_study_timestamp") val lastStudyTimestamp: Long
)
