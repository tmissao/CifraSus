package br.com.missao.cifrasus.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Kotlin extensions used by ViewGroup
 */

/**
 * Inflates a view from a [ViewGroup]
 */
fun ViewGroup.inflate(layoutId: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachRoot)
}