package com.tasknote.todo.presentation.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Note
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.tasknote.todo.domain.model.Todo
import com.tasknote.todo.domain.model.Note
import com.tasknote.todo.navigation.Route
import com.tasknote.todo.presentation.common.EmptyState
import com.tasknote.todo.presentation.feature.todo.TodoScreen
import com.tasknote.todo.presentation.feature.note.NoteScreen
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = { TaskNoteBottomNav(navController) }
    ) {
        padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { GreetingSection() }
            item { QuickActionsCard(navController) }
            item { RecentSection() }
        }
    }
}

@Composable
private fun GreetingSection() {
    val greeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 0..11 -> "Good Morning 🌅"
        in 12..16 -> "Good Afternoon 🌤"
        else -> "Good Evening 🌙"
    }
    Column {
        Text(greeting, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
        Text("Let's be productive today!", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun QuickActionsCard(navController: NavController) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Quick Actions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                ActionChip(text = "Add Todo", icon = Icons.Default.CheckCircle, onClick = { navController.navigate(Route.Todo) }, modifier = Modifier.weight(1f))
                ActionChip(text = "Add Note", icon = Icons.Default.Note, onClick = { navController.navigate(Route.Note) }, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ActionChip(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilterChip(onClick = onClick, label = { Text(text) }, leadingIcon = { Icon(icon, contentDescription = null) }, modifier = modifier)
}

@Composable
private fun RecentSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("TaskNote", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        EmptyState(message = "Your todos and notes will appear here", isNote = false)
    }
}
