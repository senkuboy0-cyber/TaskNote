# TaskNote

A modern Android Todo and Note taking app built with **Kotlin**, **Jetpack Compose**, **Material 3**, **Clean Architecture**, and **Hilt**.

---

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose + Material 3 (Material You)
- **Architecture**: Clean Architecture (Data → Domain → Presentation)
- **Dependency Injection**: Hilt
- **Local Database**: Room
- **Async**: Kotlin Coroutines + Flow
- **Navigation**: Navigation Compose
- **Pattern**: MVVM + StateFlow
- **Min SDK**: API 26 (Android 8.0)
- **Target SDK**: API 36 (Android 16)

---

## Features

### Todo
- Add, edit, delete todos
- Mark complete / uncomplete
- Set date & time + reminder
- Priority (Low, Medium, High)
- Category / Tag
- Search & filter (by date, by priority)
- View completed todos

### Notes
- Create, edit, delete notes
- Plain text + Bold/Italic formatting
- Category / Tag
- Pin important notes
- Search
- Color highlight for prioritization

---

## Project Structure

```
app/src/main/java/com/ayoncoder/tasknote/
├── data/
│   ├── local/
│   │   ├── TaskNoteDatabase.kt
│   │   ├── dao/
│   │   │   ├── TodoDao.kt
│   │   │   └── NoteDao.kt
│   │   └── entity/
│   │       ├── TodoEntity.kt
│   │       └── NoteEntity.kt
│   ├── repository/
│   │   └── TaskNoteRepositoryImpl.kt
│   └── remote/             ← Future API sync
├── domain/
│   ├── model/
│   │   ├── Todo.kt
│   │   └── Note.kt
│   ├── repository/
│   │   └── TaskNoteRepository.kt
│   └── usecase/
│       ├── AddTodoUseCase.kt
│       ├── ToggleTodoUseCase.kt
│       ├── AddNoteUseCase.kt
│       └── SearchUseCase.kt
├── presentation/
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Type.kt
│   │   └── Theme.kt
│   ├── common/
│   │   ├── TaskNoteBottomNav.kt
│   │   ├── SearchBar.kt
│   │   ├── PriorityChip.kt
│   │   └── EmptyState.kt
│   ├── todo/
│   │   ├── TodoScreen.kt
│   │   ├── TodoViewModel.kt
│   │   ├── components/
│   │   │   ├── TodoItem.kt
│   │   │   └── AddTodoDialog.kt
│   │   └── state/
│   │       └── TodoUiState.kt
│   └── note/
│       ├── NoteScreen.kt
│       ├── NoteViewModel.kt
│       ├── components/
│       │   ├── NoteCard.kt
│       │   └── AddNoteDialog.kt
│       └── state/
│           └── NoteUiState.kt
├── navigation/
│   ├── Route.kt
│   └── AppNavGraph.kt
└── di/
    └── AppModule.kt
```

---

## License

MIT
