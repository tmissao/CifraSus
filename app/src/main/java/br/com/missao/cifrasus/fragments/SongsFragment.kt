package br.com.missao.cifrasus.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.adapter.SongAdapter
import br.com.missao.cifrasus.bases.FragmentBase
import br.com.missao.cifrasus.extensions.app
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import br.com.missao.cifrasus.mvps.ListSongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.ListSongMvpRequiredViewOperations
import kotlinx.android.synthetic.main.fragment_songs.*
import kotlinx.android.synthetic.main.no_data.*
import javax.inject.Inject


/**
 * Artists Fragment display a list o songs grouped by artists
 */
class SongsFragment : FragmentBase(), ListSongMvpRequiredViewOperations {

  /** Identificador da Classe */
  override var TAG: String = SongsFragment::class.java.name

  /**
   * Presenter
   */
  @Inject lateinit var presenter: ListSongMvpPresenterOperations

  /**
   * Logger to report errors
   */
  @Inject lateinit var logger: Logger

  override fun getCustomTag(): String {
    return TAG
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_songs, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inject()
    setupComponents()
    setupEvents()
  }

  /**
   * Injects activity dependencies
   */
  private fun inject() {
    this.activity.app.getDaggerViewComponent().inject(this)
    presenter.setView(this)
  }

  private fun setupComponents() {
    rvSongs.apply {
      val linearLayout = LinearLayoutManager(this.context)
      val itemDecoration = DividerItemDecoration(this.context, linearLayout.orientation)
      setHasFixedSize(true)
      layoutManager = linearLayout
      addItemDecoration(itemDecoration)
      adapter = SongAdapter()
      visibility = View.GONE
    }

    noDataImage.apply {
      setColorFilter(ContextCompat.getColor(this.context, R.color.dividerColor),
          PorterDuff.Mode.SRC_IN)
    }

    noDataText.text = getString(R.string.no_songs)

    llContainerEmpty.visibility = View.VISIBLE
  }

  private fun setupEvents() {
  }

  override fun onResume() {
    super.onResume()
    this.getSongs()
  }

  private fun getSongs() = presenter.getSongs()

  override fun onGetSongs(musics: List<SongInfoWrapper>) {
    if (musics.isNotEmpty()) {
      (rvSongs.adapter as SongAdapter).add(musics)
      rvSongs.visibility = View.VISIBLE
      llContainerEmpty.visibility = View.INVISIBLE
    }
  }
}