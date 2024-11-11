package com.mkao.skysync.presentation.forecast.navigation

import androidx.navigation.NamedNavArgument

// Object holding specific navigation directions
object NavDirections {
    // Navigation command to the "forecast" destination
    val Forecast = object : NavigationCommands {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination: String = "forecast" // Destination route name
    }
}