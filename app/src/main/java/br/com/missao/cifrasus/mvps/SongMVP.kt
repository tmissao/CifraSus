package br.com.missao.cifrasus.mvps

import br.com.missao.cifrasus.model.wrappers.SongWrapper

/**
 * Application's Song MVP
 */

/**
 * Required View Operations
 */
interface SongMvpRequiredViewOperations {

  /**
   * Receives Song
   */
  fun onGetSong(music: SongWrapper)

  /**
   * Receives Song with tone changed
   */
  fun onChangeTone(song: SongWrapper)

}

/**
 * Operations provided by Presenter
 */
interface SongMvpPresenterOperations {

  /**
   * Obtains Song Chords by it's [id]
   */
  fun getSong(id: String)

  /**
   * Changes Music Tone by [degree]
   */
  fun changeTone(song: SongWrapper, degree: Int)

  /**
   * Defines view reference
   */
  fun setView(view: SongMvpRequiredViewOperations)

}

/**
 * Required Presenter Operations
 */
interface SongMvpRequiredPresenterOperations {

  /**
   * Receives Song
   */
  fun onGetSong(music: SongWrapper)

  /**
   * Receives Song with tone changed
   */
  fun onChangeTone(song: SongWrapper)

}

/**
 * Operations provided by Model
 */
interface SongMvpModelOperations {

  /**
   * Obtains Song Chords by it's [id]
   */
  fun getSong(id: String)

  /**
   * Changes Music Tone by [degree]
   */
  fun changeTone(song: SongWrapper, degree: Int)

  /**
   * Defines presenter reference
   */
  fun setPresenter(presenter: SongMvpRequiredPresenterOperations)
}