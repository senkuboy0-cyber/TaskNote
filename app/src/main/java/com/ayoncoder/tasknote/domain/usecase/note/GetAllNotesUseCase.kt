package com.ayoncoder.tasknote.domain.usecase.note

import com.ayoncoder.tasknote.domain.model.Note
import com.ayoncoder.tasknote.domain.repository.TaskNoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val repository: TaskNoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}
