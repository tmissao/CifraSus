package br.com.missao.cifrasus.mvps

import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper

/**
 * Application's Song MVP
 */

/**
 * Required View Operations
 */
interface ListSongMvpRequiredViewOperations {

  /**
   * Receives Songs Collection
   */
  fun onGetSongs(musics: List<SongInfoWrapper>)

}

/**
 * Operations provided by Presenter
 */
interface ListSongMvpPresenterOperations {

  /**
   * Obtains alls songs saved on device
   */
  fun getSongs()

  /**
   * Defines view reference
   */
  fun setView(view: ListSongMvpRequiredViewOperations)

}

/**
 * Required Presenter Operations
 */
interface ListSongMvpRequiredPresenterOperations {

  /**
   * Receives Songs Collection
   */
  fun onGetSongs(musics: List<SongInfoWrapper>)

}

/**
 * Operations provided by Model
 */
interface ListSongMvpModelOperations {

  /**
   * Obtains alls songs saved on device
   */
  fun getSongs()

  /**
   * Defines presenter reference
   */
  fun setPresenter(presenter: ListSongMvpRequiredPresenterOperations)
}