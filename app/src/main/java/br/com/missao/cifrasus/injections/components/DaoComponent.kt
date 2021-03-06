package br.com.missao.cifrasus.injections.components

import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.injections.modules.DaoModule
import dagger.Component
import javax.inject.Singleton

/**
 * Dao's Component
 */
@Singleton
@Component(modules = [DaoModule::class])
interface DaoComponent {

  /**
   * Obtains [SongDao]
   */
  fun getSongDao(): SongDao
}