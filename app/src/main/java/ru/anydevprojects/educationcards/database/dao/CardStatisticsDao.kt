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
        "SELECT * FROM cardentity INNER JOIN cardstatisticsentity ON cardentity.uid = cardstatisticsentity.card_id WHERE cardentity.deck_id = :deckId"
    )
    suspend fun getAllStatisticByDeckId(deckId: String): List<CardStatisticsEntity>

    @Query("DELETE FROM cardstatisticsentity WHERE card_id IN (:cardId)")
    suspend fun deleteByCardId(cardId: String)
}
