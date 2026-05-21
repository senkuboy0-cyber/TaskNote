package com.tasknote.todo.data.repository

import com.tasknote.todo.data.local.dao.NoteDao
import com.tasknote.todo.data.local.dao.TodoDao
import com.tasknote.todo.data.local.entity.NoteEntity
import com.tasknote.todo.data.local.entity.TodoEntity
import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.model.Todo.Priority
import com.tasknote.todo.domain.repository.TaskNoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskNoteRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val noteDao: NoteDao
) : TaskNoteRepository {

    override fun getAllTodos(): Flow<List<Todo>> = todoDao.getAllTodos().map { entities -> entities.map { it.toTodo() } }
    override fun getCompletedTodos(): Flow<List<Todo>> = todoDao.getCompletedTodos().map { entities -> entities.map { it.toTodo() } }
    override fun getTodoById(id: Long): Flow<Todo?> = todoDao.getTodoById(id).map { it?.toTodo() }

    override suspend fun addTodo(todo: Todo): Long = todoDao.insertTodo(todo.toEntity())
    override suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo.toEntity())
    override suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo.toEntity())

    override fun searchTodos(query: String): Flow<List<Todo>> = todoDao.searchTodos(query).map { it.map { e -> e.toTodo() } }

    override suspend fun toggleTodoCompletion(id: Long, isCompleted: Boolean) {
        val now = if (isCompleted) Date().time else null
        todoDao.updateCompletionStatus(id, isCompleted, now)
    }

    override fun getTodosByPriority(): Flow<List<Todo>> = todoDao.getTodosByPriorityHighToLow().map { it.map { e -> e.toTodo() } }
    override fun getTodosByCategory(category: String): Flow<List<Todo>> = todoDao.getTodosByCategory(category).map { it.map { e -> e.toTodo() } }

    override fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes().map { entities -> entities.map { it.toNote() } }
    override fun getNoteById(id: Long): Flow<Note?> = noteDao.getNoteById(id).map { it?.toNote() }

    override suspend fun addNote(note: Note): Long = noteDao.insertNote(note.toEntity())
    override suspend fun updateNote(note: Note) = noteDao.updateNote(note.toEntity())
    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note.toEntity())

    override fun searchNotes(query: String): Flow<List<Note>> = noteDao.searchNotes(query).map { it.map { e -> e.toNote() } }
    override suspend fun toggleNotePinned(id: Long, pinned: Boolean) = noteDao.updatePinnedStatus(id, pinned)
    override fun getNotesByCategory(category: String): Flow<List<Note>> = noteDao.getNotesByCategory(category).map { it.map { e -> e.toNote() } }

    private fun TodoEntity.toTodo() = Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = priority.toDomain(),
        category = category,
        dueDate = dueDate?.let { Date(it) },
        reminderTime = reminderTime?.let { Date(it) },
        createdAt = Date(createdAt),
        completedAt = completedAt?.let { Date(it) }
    )

    private fun Todo.toEntity() = TodoEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = priority.toDb(),
        category = category,
        dueDate = dueDate?.time,
        reminderTime = reminderTime?.time,
        createdAt = createdAt.time,
        completedAt = completedAt?.time
    )

    private fun NoteEntity.toNote() = Note(
        id = id,
        title = title,
        content = content,
        category = category,
        color = color,
        isBoldEnabled = isBoldEnabled,
        isItalicEnabled = isItalicEnabled,
        pinned = pinned,
        createdAt = Date(createdAt),
        updatedAt = Date(updatedAt)
    )

    private fun Note.toEntity() = NoteEntity(
        id = id,
        title = title,
        content = content,
        category = category,
        color = color,
        isBoldEnabled = isBoldEnabled,
        isItalicEnabled = isItalicEnabled,
        pinned = pinned,
        createdAt = createdAt.time,
        updatedAt = updatedAt.time
    )

    private fun Priority.toDb(): TodoEntity.Priority = when (this) {
        Priority.LOW -> TodoEntity.Priority.LOW
        Priority.MEDIUM -> TodoEntity.Priority.MEDIUM
        Priority.HIGH -> TodoEntity.Priority.HIGH
    }

    private fun TodoEntity.Priority.toDomain(): Priority = when (this) {
        TodoEntity.Priority.LOW -> Priority.LOW
        TodoEntity.Priority.MEDIUM -> Priority.MEDIUM
        TodoEntity.Priority.HIGH -> Priority.HIGH
    }
}
