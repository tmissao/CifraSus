package br.com.missao.cifrasus.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Helper class to convert dimensions to pixels units
 */
object PixelDimensions {

    /**
     * Converts dp to pixels
     */
    fun convertDpToPixels(dp: Float, resource: Resources): Float {
        val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, resource.displayMetrics)
        return px
    }

    /**
     * Converts pixels to dp
     */
    fun converPixelsToDp(px: Float, resource: Resources): Float {
        val dp = px / resource.displayMetrics.density
        return dp
    }

    /**
     * Converts sp to pixels
     */
    fun convertSpToPixels(sp: Float, resource: Resources): Float {
        return sp / resource.displayMetrics.scaledDensity
    }

    /**
     * Converts pixels to sp
     */
    fun convertPixelsToSp(pixels: Float, resource: Resources): Float {
        return pixels * resource.displayMetrics.scaledDensity
    }
}