package br.com.missao.cifrasus.injections.components

import br.com.missao.cifrasus.apis.ChordsAPI
import br.com.missao.cifrasus.injections.modules.ApiModule
import br.com.missao.cifrasus.injections.modules.AppModule
import br.com.missao.cifrasus.injections.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

/**
 * Applications API components
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, RetrofitModule::class))
interface ApiComponent {

  /**
   * Obtains [ChordsAPI]
   */
  fun getChordsApi(): ChordsAPI
}