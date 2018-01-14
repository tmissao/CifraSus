package br.com.missao.cifrasus.injections.components

import br.com.missao.cifrasus.injections.modules.*
import br.com.missao.cifrasus.mvps.HomeMvpPresenterOperations
import br.com.missao.cifrasus.mvps.ListSongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import dagger.Component
import javax.inject.Singleton

/**
 * Application's presenter component
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, LoggerModule::class,
    MapperModule::class, DomainModule::class, RealmModule::class, DaoModule::class, PresenterModule::class))

interface PresenterComponent {

  /**
   * Obtains [SongMvpPresenterOperations]
   */
  fun getSongMvpPresenterOperations(): SongMvpPresenterOperations

  /**
   * Obtains [ListSongMvpPresenterOperations]
   */
  fun getListSongMvpPresenterOperations(): ListSongMvpPresenterOperations

  /**
   * Obtains [HomeMvpPresenterOperations]
   */
  fun getHomeMvpPresenterOperations(): HomeMvpPresenterOperations
}