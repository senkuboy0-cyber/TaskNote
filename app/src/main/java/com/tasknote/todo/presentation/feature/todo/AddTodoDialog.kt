package com.tasknote.todo.presentation.feature.todo

import androidx.compose.runtime.Composable

@Composable
fun AddTodoDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    if (!show) return
}
