package com.tasknote.todo.domain.usecase.note

import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.repository.TaskNoteRepository

class AddNoteUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(note: Note): Long {
        return repository.addNote(note)
    }
}
