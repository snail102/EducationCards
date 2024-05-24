package ru.anydevprojects.educationcards.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val path: String
)
