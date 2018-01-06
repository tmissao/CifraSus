package br.com.missao.cifrasus.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.bases.FragmentBase

/**
 * Artists Fragment display a list o songs grouped by artists
 */
class PlaylistsFragment : FragmentBase() {

  /** Identificador da Classe */
  override var TAG: String = PlaylistsFragment::class.java.name

  override fun getCustomTag(): String {
    return TAG
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_playlists, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupComponents()
    setupEvents()
  }

  fun setupComponents() {
  }

  fun setupEvents() {
  }
}