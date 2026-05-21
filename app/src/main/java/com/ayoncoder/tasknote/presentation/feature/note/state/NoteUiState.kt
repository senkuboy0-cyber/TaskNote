package com.ayoncoder.tasknote.presentation.feature.note.state

sealed class NoteUiState {
    object Loading : NoteUiState()
    data class Success(
        val notes: List<com.ayoncoder.tasknote.domain.model.Note> = emptyList(),
        val showDialog: Boolean = false,
        val editingNote: com.ayoncoder.tasknote.domain.model.Note? = null,
        val searchQuery: String = ""
    ) : NoteUiState()
    data class Error(val message: String) : NoteUiState()
}
