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
    suspend fun insert(deckEntity: DeckEntity)

    @Query("SELECT * FROM DeckEntity WHERE uid=:id")
    suspend fun findById(id: String): DeckEntity?

    @Query("SELECT * FROM DeckEntity WHERE uid=:id")
    fun findByIdFlow(id: String): Flow<DeckEntity>

    @Query("SELECT * FROM DeckEntity")
    fun getAllDecks(): Flow<List<DeckEntity>>

    @Delete
    suspend fun delete(deckEntity: DeckEntity)
}
