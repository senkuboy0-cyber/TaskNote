package com.tasknote.todo.domain.repository

import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TaskNoteRepository {
    // Todo Operations
    fun getAllTodos(): Flow<List<Todo>>
    fun getCompletedTodos(): Flow<List<Todo>>
    fun getTodoById(id: Long): Flow<Todo?>
    suspend fun addTodo(todo: Todo): Long
    suspend fun updateTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    fun searchTodos(query: String): Flow<List<Todo>>
    suspend fun toggleTodoCompletion(id: Long, isCompleted: Boolean)
    fun getTodosByPriority(): Flow<List<Todo>>
    fun getTodosByCategory(category: String): Flow<List<Todo>>

    // Note Operations
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: Long): Flow<Note?>
    suspend fun addNote(note: Note): Long
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun searchNotes(query: String): Flow<List<Note>>
    suspend fun toggleNotePinned(id: Long, pinned: Boolean)
    fun getNotesByCategory(category: String): Flow<List<Note>>
}
