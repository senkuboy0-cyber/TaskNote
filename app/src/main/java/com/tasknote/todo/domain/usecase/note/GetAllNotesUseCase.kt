package com.tasknote.todo.domain.usecase.note

import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.repository.TaskNoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val repository: TaskNoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}
