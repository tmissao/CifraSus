package br.com.missao.cifrasus.extensions

import android.app.Activity
import br.com.missao.cifrasus.app.App


/**
 *  Kotlin extensions used by the application
 */

/**
 * Creates a final variable on Activity class to avoid unnecessary cast do [App]
 */
val Activity.app: App
    get() = application as App
