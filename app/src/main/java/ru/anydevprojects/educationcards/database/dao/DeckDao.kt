package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.educationcards.database.models.DeckEntity

@Dao
interface DeckDao {
    @Insert
    suspend fun insert(deckEntity: DeckEntity): Long

    @Query("SELECT * FROM deckentity WHERE id=:id")
    suspend fun findById(id: Long): DeckEntity?

    @Query("SELECT * FROM deckentity WHERE id=:id")
    fun findByIdFlow(id: Long): Flow<DeckEntity>

    @Query("SELECT * FROM deckentity")
    fun getAllDecks(): Flow<List<DeckEntity>>

    @Delete
    suspend fun delete(deckEntity: DeckEntity)
}
