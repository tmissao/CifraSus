package br.com.missao.cifrasus.presenters

import br.com.missao.cifrasus.bases.PresenterBase
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import br.com.missao.cifrasus.mvps.HomeMvpModelOperations
import br.com.missao.cifrasus.mvps.HomeMvpPresenterOperations
import br.com.missao.cifrasus.mvps.HomeMvpRequiredPresenterOperations
import br.com.missao.cifrasus.mvps.HomeMvpRequiredViewOperations

/**
 * Home's Presenter
 */
class HomePresenter(domain: HomeMvpModelOperations)
  : PresenterBase<HomeMvpRequiredViewOperations, HomeMvpModelOperations>(),
    HomeMvpPresenterOperations, HomeMvpRequiredPresenterOperations {

  init {
    super.domain = domain
    super.domain?.setPresenter(this)
  }

  override fun addChord(url: String) {
    domain?.addChord(url)
  }

  override fun setView(view: HomeMvpRequiredViewOperations) {
    super.view = view
  }

  override fun onAddChord(song: SongInfoWrapper) {
    view?.onAddChord(song)
  }

  override fun onAddChordError(connectionError: Boolean) {
    view?.onAddChordError(connectionError)
  }
}