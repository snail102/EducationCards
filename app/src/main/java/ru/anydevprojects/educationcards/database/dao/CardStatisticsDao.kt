package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.anydevprojects.educationcards.database.models.CardStatisticsEntity

@Dao
interface CardStatisticsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cardStatisticsEntity: CardStatisticsEntity)

    @Query(
        "UPDATE cardstatisticsentity SET number_of_studies = number_of_studies + 1, last_study_timestamp = :timestamp  WHERE card_id = :cardId"
    )
    suspend fun updateStatisticsForCard(cardId: Long, timestamp: Long)

    @Query(
        "SELECT * FROM cardentity INNER JOIN cardstatisticsentity ON cardentity.id = cardstatisticsentity.card_id WHERE cardentity.deck_id = :deckId"
    )
    suspend fun getAllStatisticByDeckId(deckId: Long): List<CardStatisticsEntity>

    @Query("DELETE FROM cardstatisticsentity WHERE card_id IN (:cardId)")
    suspend fun deleteByCardId(cardId: Long)
}
