package br.com.missao.cifrasus.presenters

import br.com.missao.cifrasus.bases.PresenterBase
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredViewOperations

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

  override fun getSong(id: String) {
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