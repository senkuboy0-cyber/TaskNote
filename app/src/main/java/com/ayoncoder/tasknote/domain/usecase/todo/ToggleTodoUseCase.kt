package com.ayoncoder.tasknote.domain.usecase.todo

import com.ayoncoder.tasknote.domain.repository.TaskNoteRepository

class ToggleTodoUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(id: Long, isCompleted: Boolean) {
        repository.toggleTodoCompletion(id, isCompleted)
    }
}
