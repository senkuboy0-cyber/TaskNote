package com.tasknote.todo.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tasknote.todo.presentation.common.TaskNoteBottomNavItem
import com.tasknote.todo.navigation.Route

sealed class TaskNoteBottomNavItem(val route: Route, val label: String, val icon: ImageVector) {
    data object Home : TaskNoteBottomNavItem(Route.Home, "Home", Icons.Rounded.Home)
    data object Todo : TaskNoteBottomNavItem(Route.Todo, "Todos", Icons.Rounded.CheckCircle)
    data object Note : TaskNoteBottomNavItem(Route.Note, "Notes", Icons.Rounded.Notes)
}

@Composable
fun TaskNoteBottomNav(navController: NavController, currentTab: String = Route.Home::class.simpleName ?: "Home") {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        TaskNoteBottom NavItem.Home.route::class.simpleName?.let { route ->
            NavigationBarItem(
                icon = { Icon(Icons.Rounded.Home, contentDescription = "Home", modifier = Modifier.size(24.dp)) },
                label = { Text("Home") },
                selected = true,
                onClick = { navController.navigate(Route.Home) { launchSingleTop = true } },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
        NavigationBarItem(
            icon = { Icon(Icons.Rounded.CheckCircle, contentDescription = "Todos", modifier = Modifier.size(24.dp)) },
            label = { Text("Todos") },
            selected = currentTab == Route.Todo::class.simpleName,
            onClick = { navController.navigate(Route.Todo) { launchSingleTop = true } },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Rounded.Notes, contentDescription = "Notes", modifier = Modifier.size(24.dp)) },
            label = { Text("Notes") },
            selected = currentTab == Route.Note::class.simpleName,
            onClick = { navController.navigate(Route.Note) { launchSingleTop = true } },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}
