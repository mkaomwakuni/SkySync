package com.mkao.skysync.presentation.forecast.navigation

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableStateFlow

// Object that manages the navigation command state
object NavManager {
    // Mutable state flow to hold the current navigation command, initialized with the default command
    val command = MutableStateFlow(NavigationCommands.Default)

    // Function to update the current navigation command to a new direction
    fun navigate(directions: NavigationCommands) {
        command.value = directions // Updates the command to trigger navigation
    }
}