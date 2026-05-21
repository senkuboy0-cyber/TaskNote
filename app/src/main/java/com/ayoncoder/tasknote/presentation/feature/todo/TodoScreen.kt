package com.ayoncoder.tasknote.presentation.feature.todo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ayoncoder.tasknote.domain.model.Todo
import com.ayoncoder.tasknote.domain.model.Todo.Priority
import com.ayoncoder.tasknote.presentation.common.EmptyState
import com.ayoncoder.tasknote.presentation.common.TaskNoteBottomNav
import com.ayoncoder.tasknote.presentation.feature.note.components.AddNoteDialog
import com.ayoncoder.tasknote.presentation.feature.note.NoteViewModel
import com.ayoncoder.tasknote.presentation.feature.todo.components.AddTodoDialog
import com.ayoncoder.tasknote.presentation.feature.todo.components.TodoItem
import com.ayoncoder.tasknote.presentation.feature.todo.state.TodoUiState
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoScreen(viewModel: TodoViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editingTodo by remember { mutableStateOf<Todo?>(null) }
    var showCompletedOnly by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { editingTodo = null; showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Todo")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Todos", style = MaterialTheme.typography.titleLarge)
                FilterChip(
                    selected = showCompletedOnly,
                    onClick = { viewModel.toggleCompletedFilter(); showCompletedOnly = !showCompletedOnly },
                    label = { Text(if (showCompletedOnly) "Completed" else "All") }
                )
            }
            when (val state = uiState) {
                is TodoUiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                is TodoUiState.Success -> {
                    val todos = state.todos
                    if (todos.isEmpty()) {
                        EmptyState(if (showCompletedOnly) "কোনো সম্পন্ন কাজ নেই" else "আকচ কিছু নেই")
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(todos) { todo ->
                                TodoItem(
                                    todo = todo,
                                    onToggle = { viewModel.onToggleTodo(todo.id, !todo.isCompleted) },
                                    onDelete = { viewModel.onDeleteTodo(todo) },
                                    onClick = { editingTodo = todo; showDialog = true }
                                )
                            }
                        }
                    }
                }
                is TodoUiState.Error -> Text("${state.message}", modifier = Modifier.padding(16.dp))
            }
        }
        AddTodoDialog(
            showDialog = showDialog,
            initialTitle = editingTodo?.title ?: "",
            initialDescription = editingTodo?.description ?: "",
            initialPriority = editingTodo?.priority ?: Priority.MEDIUM,
            onDismiss = { showDialog = false; editingTodo = null },
            onConfirm = { title, description, priority, category ->
                if (editingTodo == null) viewModel.onAddTodo(title, description, priority, category)
                else viewModel.onUpdateTodo(editingTodo!!.copy(title = title, description = description, priority = priority, category = category))
                showDialog = false; editingTodo = null
            }
        )
    }
}
