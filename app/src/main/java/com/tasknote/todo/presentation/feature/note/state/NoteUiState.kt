package com.tasknote.todo.presentation.feature.note.state

sealed class NoteUiState {
    object Loading : NoteUiState()
    data class Success(
        val notes: List<com.tasknote.todo.domain.model.Note> = emptyList(),
        val showDialog: Boolean = false,
        val editingNote: com.tasknote.todo.domain.model.Note? = null,
        val searchQuery: String = ""
    ) : NoteUiState()
    data class Error(val message: String) : NoteUiState()
}
