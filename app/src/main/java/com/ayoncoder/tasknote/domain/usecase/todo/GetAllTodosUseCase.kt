package com.ayoncoder.tasknote.domain.usecase.todo

import com.ayoncoder.tasknote.domain.model.Todo
import com.ayoncoder.tasknote.domain.repository.TaskNoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllTodosUseCase(private val repository: TaskNoteRepository) {
    operator fun invoke(): Flow<List<Todo>> {
        return repository.getAllTodos()
    }
}
