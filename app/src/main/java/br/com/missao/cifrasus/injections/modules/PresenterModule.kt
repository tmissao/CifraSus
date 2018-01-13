package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.mvps.ListSongMvpModelOperations
import br.com.missao.cifrasus.mvps.ListSongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import br.com.missao.cifrasus.presenters.SongPresenter
import br.com.missao.cifrasus.presenters.ListSongPresenter
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
}