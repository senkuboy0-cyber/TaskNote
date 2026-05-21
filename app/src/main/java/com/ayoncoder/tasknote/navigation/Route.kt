package com.ayoncoder.tasknote.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable data object Home : Route()
    @Serializable data object Todo : Route()
    @Serializable data object Note : Route()
}
