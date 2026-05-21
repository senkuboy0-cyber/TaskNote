package com.ayoncoder.tasknote.domain.usecase.todo

import com.ayoncoder.tasknote.domain.model.Todo
import com.ayoncoder.tasknote.domain.repository.TaskNoteRepository

class DeleteTodoUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}
