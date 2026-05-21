package com.ayoncoder.tasknote.domain.model

import java.util.Date

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val category: String? = null,
    val color: Int? = null,
    val isBoldEnabled: Boolean = false,
    val isItalicEnabled: Boolean = false,
    val pinned: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
