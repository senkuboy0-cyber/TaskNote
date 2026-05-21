package com.ayoncoder.tasknote.data.local.dao

import androidx.room.*
import com.ayoncoder.tasknote.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY pinned DESC, updatedAt DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Long): Flow<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE content LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%' ORDER BY pinned DESC, updatedAt DESC")
    fun searchNotes(query: String): Flow<List<NoteEntity>>

    @Query("UPDATE notes SET pinned = :pinned WHERE id = :id")
    suspend fun updatePinnedStatus(id: Long, pinned: Boolean)

    @Query("SELECT * FROM notes WHERE category = :category ORDER BY pinned DESC, updatedAt DESC")
    fun getNotesByCategory(category: String): Flow<List<NoteEntity>>
}
