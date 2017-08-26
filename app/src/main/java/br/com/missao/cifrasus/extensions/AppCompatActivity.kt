package br.com.missao.cifrasus.extensions


import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * AppCompactActivity extensions
 */

/**
 * Defines a toolbar for [AppCompatActivity]
 */
fun AppCompatActivity.setToolbar(toolbar: Toolbar, showTitle: Boolean = true, showHomeButton: Boolean = true) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(showHomeButton)
    this.supportActionBar?.setDisplayShowTitleEnabled(showTitle)

    if (!showTitle) {
        this.supportActionBar?.title = ""
    }
}

/**
 * Defines a toolbar for [AppCompatActivity] with a custom icon
 */
fun AppCompatActivity.setToolbar(toolbar: Toolbar, iconId: Int, showTitle: Boolean = true) {
    setToolbar(toolbar, showTitle)
    this.supportActionBar?.setHomeAsUpIndicator(iconId)
}

