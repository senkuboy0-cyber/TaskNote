# TaskNote

A modern Android Todo and Note taking app built with **Kotlin**, **Jetpack Compose**, **Material 3**, **Clean Architecture**, and **Hilt**.

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
- Search by title/content
- Color highlight for prioritization

### General
- Dark Mode + Light Mode (follow system)
- Material You dynamic theming (Android 12+)
- Clean Architecture
- Hilt Dependency Injection
- Room Database
- Kotlin Coroutines + Flow
- Navigation Compose
- MVVM + StateFlow
- Min SDK: API 26 (Android 8.0)
- Target SDK: API 36 (Android 16)

---

## Tech Stack

| Layer | Technology |
|-------|----------- |
| UI | Jetpack Compose + Material 3 + Material You |
| Architecture | Clean Architecture (Data → Domain → Presentation) |
| DI | Hilt |
| Local Database | Room |
| Async | Kotlin Coroutines + Flow |
| Navigation | Navigation Compose |
| Pattern | MVVM + StateFlow |

---

## Project Structure

```
app/src/main/java/com/ayoncoder/tasknote/
├── data/
│   ├── local/
│   │   ├── TaskNoteDatabase.kt          ← Room DB
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
│       ├── todo/
│       │   ├── AddTodoUseCase.kt
│       │   ├── ToggleTodoUseCase.kt
│       │   ├── DeleteTodoUseCase.kt
│       │   ├── UpdateTodoUseCase.kt
│       │   └── GetAllTodosUseCase.kt
│       └── note/
│           ├── AddNoteUseCase.kt
│           ├── DeleteNoteUseCase.kt
│           ├── UpdateNoteUseCase.kt
│           └── GetAllNotesUseCase.kt
├── presentation/
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Type.kt
│   │   └── Theme.
│   ├── common/
│   │   ├── TaskNoteBottomNav.kt
│   │   ├── SearchBar.kt
│   │   ├── PriorityChip.kt
│   │   └── EmptyState.kt
│   ├── feature/
│   │   ├── home/
│   │   │   └── HomeScreen.kt
│   │   ├── todo/
│   │   │   ├── TodoScreen.kt
│   │   │   ├── TodoViewModel.kt
│   │   │   ├── components/
│   │   │   │   ├── TodoItem.kt
│   │   │   │   └── AddTodoDialog.kt
│   │   │   └── state/
│   │   │       └── TodoUiState.kt
│   │   └── note/
│   │       ├── NoteScreen.kt
│   │       ├── NoteViewModel.kt
│   │       ├── components/
│   │       │   ├── NoteCard.kt
│   │       │   └── AddNoteDialog.kt
│   │       └── state/
│   │           └── NoteUiState.kt
├── navigation/
│   ├── Route.kt
│   └── AppNavGraph.kt
└── di/
    └── AppModule.kt

---

## Screenshots

| Home | Todo | Note |
|------|------|------|
| 🏠 | ✅ | 📝 |

---

## Building

```bash
# 1. Open in Android Studio
File → Open → Select "TaskNote" folder

# 2. Let Gradle sync finish (~2-5 min)

# 3. Enable Material You (Android 12+) dynamic theming
# Build → Make Project

# 4. Run on device/emulator (API 26+)
```

## To-Do / Future Enhancements

- [ ] Full-text search with highlighting
- [ ] Date/time picker for todo reminder
- [ ] Recurring todos
- [ ] Dark/Light theme toggle
- [ ] Categories management (CRUD)
- [ ] Database export/import (JSON)
- [ ] Cloud sync with Google Drive
- [ ] Widget support
- [ ] Voice note recording
- [ ] Rich text editor (Markdown support)

---

**Built with ❤️ by Ayon Coder.**
