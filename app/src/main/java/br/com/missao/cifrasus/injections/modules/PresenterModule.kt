package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
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
}