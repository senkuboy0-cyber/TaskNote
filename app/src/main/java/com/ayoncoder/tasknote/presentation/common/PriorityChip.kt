package com.ayoncoder.tasknote.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ayoncoder.tasknote.domain.model.Todo.Priority
import com.ayoncoder.tasknote.data.local.entity.TodoEntity.Priority as DbPriority

@Composable
fun PriorityChip(priority: Priority, modifier: Modifier = Modifier) {
    val (color, label) = when (priority) {
        Priority.HIGH -> MaterialTheme.colorScheme.errorContainer to "High"
        Priority.MEDIUM -> Color(0xFFFFFBFE) to "Medium"
        Priority.LOW -> Color(0xFFE8F5E9) to "Low"
    }
    Text(
        text = label,
        modifier = modifier
            .background(color, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.labelSmall,
        color = when (priority) {
            Priority.HIGH -> MaterialTheme.colorScheme.onErrorContainer
            Priority.MEDIUM -> MaterialTheme.colorScheme.onSurface
            Priority.LOW -> Color(0xFF2E7D32)
        }
    )
}

@Composable
fun PriorityChip(dbPriority: DbPriority, modifier: Modifier = Modifier) {
    val priority = when (dbPriority) {
        DbPriority.HIGH -> Priority.HIGH
        DbPriority.MEDIUM -> Priority.MEDIUM
        DbPriority.LOW -> Priority.LOW
    }
    PriorityChip(priority, modifier)
}
