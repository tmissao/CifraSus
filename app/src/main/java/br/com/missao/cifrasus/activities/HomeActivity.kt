package br.com.missao.cifrasus.activities

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.bases.FragmentBase
import br.com.missao.cifrasus.extensions.app
import br.com.missao.cifrasus.extensions.setToolbar
import br.com.missao.cifrasus.fragments.ArtistsFragment
import br.com.missao.cifrasus.fragments.PlaylistsFragment
import br.com.missao.cifrasus.fragments.SongsFragment
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import br.com.missao.cifrasus.mvps.HomeMvpPresenterOperations
import br.com.missao.cifrasus.mvps.HomeMvpRequiredViewOperations
import br.com.missao.cifrasus.utils.FragmentStack
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.loading.*
import java.util.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeMvpRequiredViewOperations {

  /** Identificador da Classe */
  private val TAG = HomeActivity::class.java.name

  /** Chave identificadora para armazenar os fragmentos instanciados no bundle */
  private val BUNDLE_FRAGMENT = "BUNDLE_FRAGMENT"

  /** Chave identificadora para armazenar a pilha de fragmentos no bundle */
  private val BUNDLE_FRAGMENT_STACK = "BUNDLE_FRAGMENT_STACK"

  /** Chave identificadora para armazenar o índice do atual fragmento no bundle */
  private val BUNDLE_CURRENT_FRAGMENT_INDEX = "BUNDLE_CURRENT_FRAGMENT_INDEX"

  /**
   * Presenter
   */
  @Inject lateinit var presenter: HomeMvpPresenterOperations

  /**
   * Logger to report errors
   */
  @Inject lateinit var logger: Logger

  /** Fragmentos da aplicação */
  private lateinit var mFragments: LinkedList<FragmentBase>

  /** Pilha de Fragmentos da aplicação */
  private lateinit var mStack: FragmentStack

  /**
   * Atual Fragmento da aplicacao
   */
  private var mCurrentFragmentIndex = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.home)
    inject()
    setupComponents(savedInstanceState)
    setupEvents()
  }

  /**
   * Injects activity dependencies
   */
  private fun inject() {
    this.app.getDaggerViewComponent().inject(this)
    presenter.setView(this)
  }

  /**
   * Configures View to be shown
   */
  private fun setupComponents(savedInstanceState: Bundle?) {

    // It is necessary to cast appbarSong as Toolbar because the include tag on XML file. Since
    // include tag loads a view type
    this.setToolbar(appbar as Toolbar, showTitle = true, showHomeButton = false)
    mFragments = savedInstanceState?.getSerializable(BUNDLE_FRAGMENT) as?
                     LinkedList<FragmentBase> ?: createFragments()
    mStack = savedInstanceState?.getSerializable(BUNDLE_FRAGMENT_STACK) as?
                 FragmentStack ?: FragmentStack()
    mCurrentFragmentIndex = savedInstanceState?.getSerializable(BUNDLE_CURRENT_FRAGMENT_INDEX) as?
                                Int ?: 0
    showFragment(mFragments.first, 0, null)
  }

  private fun setupEvents() {
    bottom_navigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.navigation_artists  -> showFragment(mFragments[0], 0, null);
        R.id.navigation_songs    -> showFragment(mFragments[1], 1, null)
        R.id.navigation_playlist -> showFragment(mFragments[2], 2, null)
      }
      true
    }

    fabAdd.setOnClickListener { showDialogAddChord() }
  }

  private fun showFragment(fragment: FragmentBase, fragmentIndex: Int, bundle: Bundle?) {
    var nextFragment = fragment
    val copy = supportFragmentManager.findFragmentByTag(fragment.getCustomTag())
    val ft = supportFragmentManager.beginTransaction()
    if (!mStack.empty()) {
      if (fragmentIndex > mCurrentFragmentIndex) {
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
      } else {
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
      }
    }

    if (copy != null) {
      nextFragment = copy as FragmentBase
    }

    if (bundle != null) {
      nextFragment.arguments = bundle
    }

    ft.replace(R.id.container, nextFragment, nextFragment.getCustomTag()).commit()
    mStack.push(nextFragment)
    mCurrentFragmentIndex = fragmentIndex
  }

  /**
   * Create Fragments
   */
  private fun createFragments(): LinkedList<FragmentBase> {
    val fragments = LinkedList<FragmentBase>()
    fragments.push(PlaylistsFragment())
    fragments.push(SongsFragment())
    fragments.push(ArtistsFragment())

    return fragments
  }

  /**
   * Shows dialog to add a chord
   */
  private fun showDialogAddChord() {
    val inflater = this.layoutInflater
    lateinit var chordUrl: EditText
    lateinit var dialog: AlertDialog

    val dialogView = inflater.inflate(R.layout.dialog_add_chord, null).apply {
      chordUrl = findViewById<EditText>(R.id.etChordURL)
    }
    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle).apply {
      this.setView(dialogView)
      this.setTitle(getString(R.string.add_chord_title))
      this.setPositiveButton(getString(R.string.add_chord_positive_button_text)) { _, _ ->
        addChord(chordUrl.text.toString())
      }
      this.setNegativeButton(getString(R.string.negative_button_text), null)
    }

    chordUrl?.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
      override fun afterTextChanged(text: Editable?) {
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = text?.isNotEmpty() ?: false
      }
    })

    dialog = builder.create()
    dialog.show()
    dialog.getButton(DialogInterface.BUTTON_POSITIVE).isEnabled = false
    dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        .setTextColor(ContextCompat.getColor(this, R.color.secondaryTextColor))
  }

  /**
   * Displays/ Hides the loading view
   */
  private fun displayLoadingView(display: Boolean) {
    fl_loading.visibility = if (display) View.VISIBLE else View.GONE
  }

  /**
   * Retrieves the chord from a specific URL
   */
  private fun addChord(url: String) {
    displayLoadingView(true)
    presenter.addChord(url)
  }

  /**
   * Displays the chord added
   */
  override fun onAddChord(song: SongInfoWrapper) {
    displayLoadingView(false)
    this.startActivity(SongActivity.getStartIntent(this, song.id))
  }

  /**
   * Notifies about an error the request chord process
   */
  override fun onAddChordError(connectionError: Boolean) {
    displayLoadingView(false)

    val title = if (connectionError) getString(R.string.connection_error_title)
    else getString(R.string.add_chord_title_error)

    val message = if (connectionError) getString(R.string.connection_error_message)
    else getString(R.string.add_chord_message_error)

    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle).apply {
      this.setTitle(title)
      this.setMessage(message)
      this.setPositiveButton(getString(R.string.positive_button_text), null)
    }

    builder.show()
  }
}
