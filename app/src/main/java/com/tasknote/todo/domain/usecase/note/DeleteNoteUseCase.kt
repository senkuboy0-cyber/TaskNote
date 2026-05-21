package com.tasknote.todo.domain.usecase.note

import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.repository.TaskNoteRepository

class DeleteNoteUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
