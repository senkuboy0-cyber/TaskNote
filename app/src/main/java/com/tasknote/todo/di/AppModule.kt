package com.tasknote.todo.di

import android.content.Context
import androidx.room.Room
import com.tasknote.todo.data.local.TaskNoteDatabase
import com.tasknote.todo.data.local.dao.NoteDao
import com.tasknote.todo.data.local.dao.TodoDao
import com.tasknote.todo.data.repository.TaskNoteRepositoryImpl
import com.tasknote.todo.domain.repository.TaskNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskNoteDatabase(@ApplicationContext context: Context): TaskNoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TaskNoteDatabase::class.java,
            "tasknote_database"
        ).build()
    }

    @Provides
    fun provideTodoDao(database: TaskNoteDatabase): TodoDao = database.todoDao()

    @Provides
    fun provideNoteDao(database: TaskNoteDatabase): NoteDao = database.noteDao()

    @Provides
    @Singleton
    fun provideTaskNoteRepository(impl: TaskNoteRepositoryImpl): TaskNoteRepository = impl
}
