package com.tasknote.todo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasknote.todo.data.local.dao.NoteDao
import com.tasknote.todo.data.local.dao.TodoDao
import com.tasknote.todo.data.local.entity.NoteEntity
import com.tasknote.todo.data.local.entity.TodoEntity

@Database(
    entities = [TodoEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskNoteDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: TaskNoteDatabase? = null

        fun getDatabase(context: Context): TaskNoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskNoteDatabase::class.java,
                    "tasknote_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
