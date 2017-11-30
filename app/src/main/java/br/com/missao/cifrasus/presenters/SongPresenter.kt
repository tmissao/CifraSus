package br.com.missao.cifrasus.presenters

import br.com.missao.cifrasus.bases.PresenterBase
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.*

/**
 * Song's Presenter
 */
class SongPresenter(domain: SongMvpModelOperations)
  : PresenterBase<SongMvpRequiredViewOperations, SongMvpModelOperations>(),
    SongMvpPresenterOperations, SongMvpRequiredPresenterOperations {

  init {
    super.domain = domain
    super.domain?.setPresenter(this)
  }

  override fun getSong(id: Long) {
    domain?.getSong(id)
  }

  override fun changeTone(song: SongWrapper, degree: Int) {
    domain?.changeTone(song, degree)
  }

  override fun setView(view: SongMvpRequiredViewOperations) {
    super.view = view
  }

  override fun onGetSong(music: SongWrapper) {
    view?.onGetSong(music)
  }

  override fun onChangeTone(song: SongWrapper) {
    view?.onChangeTone(song)
  }
}