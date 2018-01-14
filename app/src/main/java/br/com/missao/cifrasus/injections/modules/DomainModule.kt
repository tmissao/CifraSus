package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.apis.ChordsAPI
import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.domains.HomeDomain
import br.com.missao.cifrasus.domains.ListSongDomain
import br.com.missao.cifrasus.domains.SongDomain
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.ChordMapper
import br.com.missao.cifrasus.mappers.SongMapper
import br.com.missao.cifrasus.mvps.HomeMvpModelOperations
import br.com.missao.cifrasus.mvps.ListSongMvpModelOperations
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

  /**
   * Provides [ListSongMvpModelOperations]
   */
  @Provides
  @Singleton
  fun providesListSongDomain(songDao: SongDao, songMapper: SongMapper, logger: Logger):
      ListSongMvpModelOperations
      = ListSongDomain(songDao, songMapper, logger)

  /**
   * Provides [HomeMvpModelOperations]
   */
  @Provides
  @Singleton
  fun providesHomeDomain(songDao: SongDao, songMapper: SongMapper, chordMapper: ChordMapper, api:
  ChordsAPI, logger: Logger):
      HomeMvpModelOperations
      = HomeDomain(songDao, songMapper, chordMapper, api, logger)
}
