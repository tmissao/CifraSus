package br.com.missao.cifrasus.presenters

import br.com.missao.cifrasus.bases.PresenterBase
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
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

  override fun setView(view: SongMvpRequiredViewOperations) {
    super.view = view
  }

  override fun onGetSong(music: List<PhraseWrapper>) {
    view?.onGetSong(music)
  }
}