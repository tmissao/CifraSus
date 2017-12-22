package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.domains.SongDomain
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.SongMapper
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
  fun providesSongDomain(songDao: SongDao, songMapper: SongMapper, logger: Logger):
      SongMvpModelOperations
      = SongDomain(songDao, songMapper, logger)
}
