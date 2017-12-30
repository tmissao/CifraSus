package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.database.daos.SongDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dao's Module
 */
@Module
class DaoModule {

  @Provides
  @Singleton
  fun providesSongDao() = SongDao()
}