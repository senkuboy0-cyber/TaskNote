package com.tasknote.todo.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tasknote.todo.presentation.feature.todo.TodoScreen
import com.tasknote.todo.presentation.feature.note.NoteScreen
import com.tasknote.todo.presentation.feature.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier
    ) {
        composable<Route.Home> { HomeScreen(navController) }
        composable<Route.Todo> { TodoScreen() }
        composable<Route.Note> { NoteScreen() }
    }
}
