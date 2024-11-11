package com.mkao.skysync.presentation.forecast.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommands {
    val arguments : List<NamedNavArgument> // List of arguments required by the destination
    val destination: String // String representing the navigation destination

    companion object {
        // Default navigation command with no arguments and an empty destination
        val Default = object : NavigationCommands {
            override val arguments = emptyList<NamedNavArgument>()
            override val destination = ""
        }
    }
}