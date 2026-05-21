package com.tasknote.todo.domain.usecase.todo

import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.repository.TaskNoteRepository

class DeleteTodoUseCase(private val repository: TaskNoteRepository) {
    suspend operator fun invoke(todo: Todo) {
        repository.deleteTodo(todo)
    }
}
