package br.com.missao.cifrasus.extensions

import android.graphics.Rect
import android.widget.TextView

/**
 *  Kotlin TextView extensions
 */

/**
 * Gets the intrinsic width of the text inside the [TextView]
 */
fun TextView.getIntrinsicWidth(text: String): Int {
    val paint = this.paint
    val bounds = Rect()

    paint.getTextBounds(text, 0, text.length, bounds)
    return bounds.width() + bounds.left + 1
}

/**
 * Gets the character width inside the [TextView], this measure is calculated using
 * the standard deviation of each alphabetic character.
 */
fun TextView.getCharacterPixelAverage(): Int {
    val fullString = "abcdefghijklmnopqrstuvxwyz"
    val characters = fullString.toCharArray()
    var charactersWidth = arrayListOf<Float>()
    var variance = 0.0
    var average = 0.0


    for (character in characters) {
        val characterWidth = getIntrinsicWidth(character.toString())
        charactersWidth.add(characterWidth.toFloat())
        average += characterWidth
    }

    average /= characters.size

    for (characterWidth in charactersWidth) {
        variance += Math.pow((characterWidth - average), 2.toDouble()).toFloat()
    }

    variance /= charactersWidth.size
    val standardDeviation = Math.sqrt(variance)
    val charPixelSize = Math.round(average - (standardDeviation / 4)).toInt()

    return charPixelSize
}

/**
 * Checks if the [text] can be displayed inside the [TextView] without being ellipsized.
 * The calculation has a positive error margin of [threshold]
 */
fun TextView.isAllTextDisplayed(text: String, threshold: Int = 0): Boolean {
    val intrinsicWidth = this.getIntrinsicWidth(text) + threshold
    val width = this.width
    return width > intrinsicWidth
}