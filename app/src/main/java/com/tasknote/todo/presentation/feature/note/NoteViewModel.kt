package com.tasknote.todo.presentation.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.domain.usecase.note.*
import com.tasknote.todo.presentation.feature.note.state.NoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NoteUiState>(NoteUiState.Loading)
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getAllNotesUseCase().collect { notes ->
                _uiState.value = NoteUiState.Success(notes = notes)
            }
        }
    }

    fun onAddNote(title: String, content: String, category: String? = null) {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = content,
                category = category
            )
            addNoteUseCase(note)
        }
    }

    fun onDeleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun onUpdateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note.copy(updatedAt = Date()))
        }
    }
}
