package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.apis.ChordsAPI
import br.com.missao.cifrasus.apis.ChordsRest
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Application's API module
 */
@Module
class ApiModule {

  /**
   * Provides [ChordsAPI] using [retrofit] as implementation
   */
  @Provides
  @Singleton
  fun providesChordApi(retrofit: Retrofit): ChordsAPI
      = ChordsRest(retrofit)
}
