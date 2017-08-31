package br.com.missao.cifrasus.app

import android.Manifest

/**
 * Configurações gerais da aplicação
 */
class AppPreferences {

  companion object {
    @JvmField val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    @JvmField val ANIMATION_FAST = 200L
    @JvmField val ANIMATION_MEDIUM = 400L
    @JvmField val ANIMATION_LONG = 500L
  }
}