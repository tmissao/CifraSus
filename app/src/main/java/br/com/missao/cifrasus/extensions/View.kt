package br.com.missao.cifrasus.extensions

import android.view.View
import android.view.ViewTreeObserver

/**
 *  Kotlin [View] extensions
 */

/**
 * Register a callback to be executed after the view's height and width properties being
 * computed
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}
