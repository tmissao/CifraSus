package br.com.missao.cifrasus.injections.components

import android.content.Context
import br.com.missao.cifrasus.app.App
import br.com.missao.cifrasus.injections.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Application's component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    /**
     * Obtains [App]
     */
    fun getApp(): App

    /**
     * Obtains [App]
     */
    fun getContext(): Context
}
