package br.com.missao.cifrasus.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.extensions.app
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.mvps.SongMvpPresenterOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredViewOperations
import javax.inject.Inject

class SongActivity : AppCompatActivity(), SongMvpRequiredViewOperations {

  /**
   * Presenter
   */
  @Inject lateinit var presenter: SongMvpPresenterOperations

  /**
   * Logger to report errors
   */
  @Inject lateinit var logger: Logger

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_song)
    inject()
    presenter.getSong(1)
  }

  /**
   * Injects activity dependencies
   */
  fun inject() {
    this.app.getDaggerViewComponent().inject(this)
    presenter.setView(this)
  }

  override fun onGetSong(music: List<PhraseWrapper>) {
    music.forEach {
      logger.d("SongActivity", it.text)
    }
  }
}
