package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.domains.SongDomain
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Domain module
 */
@Module
class DomainModule {

  /**
   * Provides [SongMvpModelOperations]
   */
  @Provides
  @Singleton
  fun providesSongDomain(songDao: SongDao): SongMvpModelOperations
      = SongDomain(songDao)
}
