package com.tasknote.todo.presentation.feature.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tasknote.todo.presentation.feature.note.state.NoteUiState
import com.tasknote.todo.data.local.entity.NoteEntity

@Composable
fun AddNoteDialog(
    initialTitle: String = "",
    initialContent: String = "",
    initialCategory: String = "",
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (title: String, content: String, category: String) -> Unit
) {
    if (!showDialog) return

    var title by remember { mutableStateOf(initialTitle) }
    var content by remember { mutableStateOf(initialContent) }
    var category by remember { mutableStateOf(initialCategory) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initialTitle.isEmpty()) "Add Note" else "Edit Note") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
                    maxLines = 10
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (optional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(title, content, category) }, enabled = title.isNotBlank() && content.isNotBlank()) {
                Text(if (initialTitle.isEmpty()) "Add" else "Save")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
