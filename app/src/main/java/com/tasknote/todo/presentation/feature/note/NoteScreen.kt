package com.tasknote.todo.presentation.feature.note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.presentation.common.EmptyState
import com.tasknote.todo.presentation.common.TaskNoteBottomNav
import com.tasknote.todo.presentation.feature.note.components.AddNoteDialog
import com.tasknote.todo.presentation.feature.note.components.NoteCard
import com.tasknote.todo.presentation.feature.note.state.NoteUiState

@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { editingNote = null; showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text("Notes", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            when (val state = uiState) {
                is NoteUiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                is NoteUiState.Success -> {
                    val notes = state.notes
                    if (notes.isEmpty()) {
                        EmptyState(isNote = true)
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(notes) { note ->
                                NoteCard(
                                    note = note,
                                    onClick = { editingNote = note; showDialog = true },
                                    onDelete = { viewModel.onDeleteNote(note) },
                                    onTogglePin = { viewModel.onUpdateNote(note.copy(pinned = !note.pinned)) }
                                )
                            }
                        }
                    }
                }
                is NoteUiState.Error -> Text("${state.message}", modifier = Modifier.padding(16.dp))
            }
        }
        AddNoteDialog(
            showDialog = showDialog,
            initialTitle = editingNote?.title ?: "",
            initialContent = editingNote?.content ?: "",
            onDismiss = { showDialog = false; editingNote = null },
            onConfirm = { title, content, category ->
                if (editingNote == null) viewModel.onAddNote(title, content, category)
                else viewModel.onUpdateNote(editingNote!!.copy(title = title, content = content, category = category))
                showDialog = false; editingNote = null
            }
        )
    }
}
