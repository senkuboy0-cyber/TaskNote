package com.tasknote.todo.domain.usecase.todo

import com.tasknote.todo.domain.repository.TaskNoteRepository

class ToggleTodoUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(id: Long, isCompleted: Boolean) {
        repository.toggleTodoCompletion(id, isCompleted)
    }
}
