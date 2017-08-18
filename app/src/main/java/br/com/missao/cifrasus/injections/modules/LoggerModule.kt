package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.utils.ProductionLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Logger
 */
@Module
class LoggerModule {

    /**
     * Provides [Logger]
     */
    @Provides @Singleton fun providesLogger(): Logger = ProductionLogger()
}