package com.mkao.skysync.presentation.forecast.components.surface

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mkao.skysync.domain.model.DailyForecasts
import com.mkao.skysync.presentation.utils.BackgroundAnimationDuration
import com.mkao.skysync.presentation.utils.generateGradientFeel

@Composable
fun ForecastSurface(
    selectedDailyForecast: DailyForecasts, // Forecast data used to determine background color
    content: @Composable() -> Unit // Composable content to be displayed within the surface
) {
    // Animate the background color based on the forecast
    val backgroundFeel by animateColorAsState(
        targetValue = selectedDailyForecast.generateColorBasedOnForecast(), // Target color based on forecast
        animationSpec = tween(BackgroundAnimationDuration), // Animation specification for smooth transition
        label = "" // Optional label for the animated color state
    )

    // Surface composable to provide a background and layout wrapper
    Surface(
        modifier = Modifier
            .testTag("Forecast Background") // Testing tag for UI testing
            .fillMaxSize()
            .background(generateGradientFeel(backgroundFeel)) // Sets a gradient background based on color
    ) {
        // Column composable for vertical alignment of content
        Column(modifier = Modifier.fillMaxSize()) {
            content() // Calls the content composable, allowing flexible content to be displayed
        }
    }
}
