package com.ayoncoder.tasknote.presentation.feature.todo.state

import com.ayoncoder.tasknote.domain.model.Priority
nonsealed class TodoUiState {
    object Loading : TodoUiState()
    data class Success(
        val todos: List<com.ayoncoder.tasknote.domain.model.Todo> = emptyList(),
        val showDialog: Boolean = false,
        val editingTodo: com.ayoncoder.tasknote.domain.model.Todo? = null,
        val filterPriority: Priority? = null,
        val showCompletedOnly: Boolean = false
    ) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}
