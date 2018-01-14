package br.com.missao.cifrasus.mvps

import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper

/**
 * Application's Song MVP
 */

/**
 * Required View Operations
 */
interface HomeMvpRequiredViewOperations {

  /**
   * Displays the chord added
   */
  fun onAddChord(song: SongInfoWrapper)

  /**
   * Notifies about an error the request chord process
   */
  fun onAddChordError(connectionError: Boolean)

}

/**
 * Operations provided by Presenter
 */
interface HomeMvpPresenterOperations {

  /**
   * Retrieves the chord from a specific URL
   */
  fun addChord(url: String)

  /**
   * Defines view reference
   */
  fun setView(view: HomeMvpRequiredViewOperations)

}

/**
 * Required Presenter Operations
 */
interface HomeMvpRequiredPresenterOperations {

  /**
   * Displays the chord added
   */
  fun onAddChord(song: SongInfoWrapper)

  /**
   * Notifies about an error the request chord process
   */
  fun onAddChordError(connectionError: Boolean)

}

/**
 * Operations provided by Model
 */
interface HomeMvpModelOperations {

  /**
   * Retrieves the chord from a specific URL
   */
  fun addChord(url: String)

  /**
   * Defines presenter reference
   */
  fun setPresenter(presenter: HomeMvpRequiredPresenterOperations)
}