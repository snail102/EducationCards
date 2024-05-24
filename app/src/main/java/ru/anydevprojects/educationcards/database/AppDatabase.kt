package ru.anydevprojects.educationcards.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.CardStatisticsDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.database.dao.DeckStatisticsDao
import ru.anydevprojects.educationcards.database.models.CardEntity
import ru.anydevprojects.educationcards.database.models.CardStatisticsEntity
import ru.anydevprojects.educationcards.database.models.DeckEntity
import ru.anydevprojects.educationcards.database.models.DeckStatisticsEntity

@Database(
    entities = [
        DeckEntity::class,
        CardEntity::class,
        DeckStatisticsEntity::class,
        CardStatisticsEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
    abstract fun deckStatisticsDao(): DeckStatisticsDao
    abstract fun cardStatisticsDao(): CardStatisticsDao
}
