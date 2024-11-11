package com.mkao.skysync.presentation.utils

import androidx.annotation.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import kotlin.math.roundToInt


@Size(3)
private fun Color.toHSL(@Size(3) hsl: FloatArray = FloatArray(3)): FloatArray {
    //determine the maximum and minimum RGB to calculate lightness
    val max = red.coerceAtLeast(green.coerceAtLeast(blue))
    val min = red.coerceAtMost(green.coerceAtMost(blue))
    hsl[2] = (max + min) / 2 //set brightness to average of max and min

    if (max == min) {
        hsl[1] = 0f
        hsl[0] = hsl[1]
    }else {
        val delta = max- min
        hsl[1] = if (hsl[2] <= 0.5f) delta / (2 - max - min) else delta/(max + min)

        //calculate the hue based color channel is the max
        when(max) {
            red -> hsl[0] = (green - blue) /delta + (if (green < blue) 6 else 0)
            green -> hsl[0] = (blue - red) / delta + 2
            blue -> hsl[0] = (red - green) / delta + 4
        }
        hsl[0] /= 6f
    }
    return hsl //return the hsl array
}

// Converts an HSL color to RGB format, returning it as a Color.
private fun hslToColor(@Size(3) hsl: FloatArray): Color {
    val r: Float
    val g: Float
    val b: Float

    val h = hsl[0]  // Hue
    val s = hsl[1]  // Saturation
    val l = hsl[2]  // Lightness

    if (s == 0f) {
        // If saturation is zero, color is gray.
        b = l
        g = b
        r = g
    } else {
        // Calculate RGB values using the helper function hue2rgb.
        val q = if (l < 0.5f) l * (1 + s) else l + s - l * s
        val p = 2 * l - q
        r = hue2rgb(p, q, h + 1f / 3)
        g = hue2rgb(p, q, h)
        b = hue2rgb(p, q, h - 1f / 3)
    }

    return Color(r, g, b)  // Return the RGB color.
}
    // Helper function to convert hue to RGB based on intermediate values.
    private fun hue2rgb(p: Float, q: Float, t: Float): Float {
        var valueT = t
        if (valueT < 0) valueT += 1f
        if (valueT > 1) valueT -= 1f
        if (valueT < 1f / 6) return p + (q - p) * 6f * valueT
        if (valueT < 1f / 2) return q
        return if (valueT < 2f / 3) p + (q - p) * (2f / 3 - valueT) * 6f else p
    }

    // Lightens the color by increasing the lightness component of its HSL representation.
    fun Color.lightenColor(value: Float): Color {
        val hsl = toHSL()
        hsl[2] += value  // Increase lightness.
        hsl[2] = 0f.coerceAtLeast(hsl[2].coerceAtMost(1f))  // Clamp lightness to range [0, 1].
        return hslToColor(hsl)  // Return the lightened color.
    }

    // Darkens the color by decreasing the lightness component of its HSL representation.
    fun Color.darkenColor(value: Float): Color {
        val hsl = toHSL()
        hsl[2] -= value  // Decrease lightness.
        hsl[2] = 0f.coerceAtLeast(hsl[2].coerceAtMost(1f))  // Clamp lightness to range [0, 1].
        return hslToColor(hsl)  // Return the darkened color.
    }

    // Checks if the color is considered light by evaluating its lightness in the HSL model.
    fun Color.isLightColor(hsl: FloatArray = FloatArray(3)): Boolean {
        ColorUtils.RGBToHSL((red * 255f).roundToInt(), (green * 255f).roundToInt(), (blue * 255f).roundToInt(), hsl)
        return hsl[2] < .5f  // Return true if lightness is less than 0.5 (dark).
    }

    // Checks if the color is considered dark by evaluating its lightness in the HSL model.
    fun Color.isDarkColor(hsl: FloatArray = FloatArray(3)): Boolean {
        ColorUtils.RGBToHSL((red * 255f).roundToInt(), (green * 255f).roundToInt(), (blue * 255f).roundToInt(), hsl)
        return hsl[2] < .5f  // Return true if lightness is less than 0.5 (dark).
    }

    // Generates a gradient with the base color at the top and a lighter version of it at the bottom.
    fun generateGradientFeel(baseColor: Color, lightenFactor: Float = 0.3f): Brush {
        val lightFactor = lightenFactor.coerceIn(0f, 1f)  // Clamp the lightening factor to range [0, 1].

        return Brush.verticalGradient(
            colors = listOf(
                baseColor,  // Base color at the top.
                baseColor.lightenColor(lightFactor)  // Lighter color at the bottom.
            )
        )
    }