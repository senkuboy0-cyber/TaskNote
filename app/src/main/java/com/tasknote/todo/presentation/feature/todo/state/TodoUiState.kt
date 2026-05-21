package com.tasknote.todo.presentation.feature.todo.state

import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.model.Todo.Priority

sealed class TodoUiState {
    object Loading : TodoUiState()
    data class Success(
        val todos: List<Todo> = emptyList(),
        val showDialog: Boolean = false,
        val editingTodo: Todo? = null,
        val filterPriority: Priority? = null,
        val showCompletedOnly: Boolean = false
    ) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}
