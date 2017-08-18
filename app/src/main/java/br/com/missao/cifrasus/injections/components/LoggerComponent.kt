package br.com.missao.cifrasus.injections.components

import br.com.missao.cifrasus.injections.modules.LoggerModule
import br.com.missao.cifrasus.interfaces.Logger
import dagger.Component
import javax.inject.Singleton

/**
 * Application's Logger component
 */
@Singleton
@Component(modules = arrayOf(LoggerModule::class))
interface LoggerComponent {

    /**
     * Obtains [Logger]
     */
    fun getLogger(): Logger
}