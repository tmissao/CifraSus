package br.com.missao.cifrasus.mvps

import br.com.missao.cifrasus.model.wrappers.PhraseWrapper

/**
 * Application's Song MVP
 */

/**
 * Required View Operations
 */
interface SongMvpRequiredViewOperations {

    /**
     * Receives Songs chords
     */
    fun onGetSong(music: List<PhraseWrapper>)

}

/**
 * Operations provided by Presenter
 */
interface SongMvpPresenterOperations {

    /**
     * Obtains Song Chords by it's [id]
     */
    fun getSong(id: Long)

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
     * Receives Songs chords
     */
    fun onGetSong(music: List<PhraseWrapper>)

}

/**
 * Operations provided by Model
 */
interface SongMvpModelOperations {

    /**
     * Obtains Song Chords by it's [id]
     */
    fun getSong(id: Long)

    /**
     * Defines presenter reference
     */
    fun setPresenter(presenter: SongMvpRequiredPresenterOperations)
}