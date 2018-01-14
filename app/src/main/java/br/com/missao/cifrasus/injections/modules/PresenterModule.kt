package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.mvps.*
import br.com.missao.cifrasus.presenters.HomePresenter
import br.com.missao.cifrasus.presenters.ListSongPresenter
import br.com.missao.cifrasus.presenters.SongPresenter
import dagger.Module
import dagger.Provides

/**
 * Application's Presenter Module
 */
@Module
class PresenterModule {

  /**
   * Provides [SongMvpPresenterOperations]
   */
  @Provides
  fun providesSongPresenter(domain: SongMvpModelOperations): SongMvpPresenterOperations
      = SongPresenter(domain)

  /**
   * Provides [ListSongMvpPresenterOperations]
   */
  @Provides
  fun providesListSongPresenter(domain: ListSongMvpModelOperations): ListSongMvpPresenterOperations
      = ListSongPresenter(domain)

  /**
   * Provides [HomeMvpPresenterOperations]
   */
  @Provides
  fun providesHomePresenter(domain: HomeMvpModelOperations): HomeMvpPresenterOperations
      = HomePresenter(domain)
}