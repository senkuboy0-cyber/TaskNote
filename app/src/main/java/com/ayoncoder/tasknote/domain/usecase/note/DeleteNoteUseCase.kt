package com.ayoncoder.tasknote.domain.usecase.note

import com.ayoncoder.tasknote.domain.model.Note
import com.ayoncoder.tasknote.domain.repository.TaskNoteRepository

class DeleteNoteUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
