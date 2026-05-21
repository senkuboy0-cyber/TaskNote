package com.tasknote.todo.presentation.feature.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.model.Todo.Priority
import com.tasknote.todo.domain.usecase.todo.*
import com.tasknote.todo.presentation.feature.todo.state.TodoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val getCompletedTodosUseCase: GetAllTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCompletedTodosUseCase().collect { todos ->
                _uiState.value = TodoUiState.Success(todos = todos)
            }
        }
    }

    fun onAddTodo(title: String, description: String? = null, priority: Priority = Priority.MEDIUM, category: String? = null) {
        viewModelScope.launch {
            val newTodo = Todo(
                title = title,
                description = description,
                priority = priority,
                category = category
            )
            addTodoUseCase(newTodo)
        }
    }

    fun onToggleTodo(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            toggleTodoUseCase(id, isCompleted)
        }
    }

    fun onDeleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    fun onUpdateTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
        }
    }

    fun toggleCompletedFilter() {
        val state = _uiState.value as? TodoUiState.Success ?: return
        _uiState.value = state.copy(showCompletedOnly = !state.showCompletedOnly)
        viewModelScope.launch {
            if (state.showCompletedOnly) {
                getCompletedTodosUseCase().collect { _uiState.value = TodoUiState.Success(todos = it, showCompletedOnly = false) }
            } else {
                getCompletedTodosUseCase().collect { _uiState.value = TodoUiState.Success(todos = it, showCompletedOnly = true) }
            }
        }
    }
}
