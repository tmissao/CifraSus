package br.com.missao.cifrasus.presenters

import br.com.missao.cifrasus.bases.PresenterBase
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.ListSongMvpModelOperations
import br.com.missao.cifrasus.mvps.ListSongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.ListSongMvpRequiredPresenterOperations
import br.com.missao.cifrasus.mvps.ListSongMvpRequiredViewOperations

/**
 * Song's Presenter
 */
class ListSongPresenter(domain: ListSongMvpModelOperations)
  : PresenterBase<ListSongMvpRequiredViewOperations, ListSongMvpModelOperations>(),
    ListSongMvpPresenterOperations, ListSongMvpRequiredPresenterOperations {

  init {
    super.domain = domain
    super.domain?.setPresenter(this)
  }

  override fun getSongs() {
    domain?.getSongs()
  }

  override fun setView(view: ListSongMvpRequiredViewOperations) {
    super.view = view
  }

  override fun onGetSongs(musics: List<SongInfoWrapper>) {
    view?.onGetSongs(musics)
  }
}