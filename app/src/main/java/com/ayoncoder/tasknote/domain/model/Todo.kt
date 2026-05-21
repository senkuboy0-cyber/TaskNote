package com.ayoncoder.tasknote.domain.model

import java.util.Date

data class Todo(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val category: String? = null,
    val dueDate: Date? = null,
    val reminderTime: Date? = null,
    val createdAt: Date = Date(),
    val completedAt: Date? = null
) {
    enum class Priority { LOW, MEDIUM, HIGH }
}
