package com.tasknote.todo.domain.usecase.todo

import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.repository.TaskNoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodosUseCase(private val repository: TaskNoteRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getAllTodos()
    }
}
