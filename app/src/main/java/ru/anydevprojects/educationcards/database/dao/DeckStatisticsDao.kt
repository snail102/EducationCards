package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anydevprojects.educationcards.database.models.DeckStatisticsEntity

@Dao
interface DeckStatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(deckStatistics: DeckStatisticsEntity)

    @Query("SELECT * FROM deckstatisticsentity WHERE deck_id=:deckId")
    suspend fun getDeckStatisticsById(deckId: Long): DeckStatisticsEntity

    @Query(
        "UPDATE deckstatisticsentity SET number_of_studies = number_of_studies + 1, last_study_timestamp = :timestamp WHERE deck_id = :deckId"
    )
    suspend fun updateStatisticsForDeck(deckId: Long, timestamp: Long)

    @Query("DELETE FROM deckstatisticsentity WHERE deck_id IN (:deckId)")
    suspend fun deleteByDeckId(deckId: Long)
}
