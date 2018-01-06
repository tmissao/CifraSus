package br.com.missao.cifrasus.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.bases.FragmentBase
import br.com.missao.cifrasus.extensions.setToolbar
import br.com.missao.cifrasus.fragments.ArtistsFragment
import br.com.missao.cifrasus.fragments.PlaylistsFragment
import br.com.missao.cifrasus.fragments.SongsFragment
import br.com.missao.cifrasus.utils.FragmentStack
import kotlinx.android.synthetic.main.home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

  /** Identificador da Classe */
  private val TAG = HomeActivity::class.java.name

  /** Chave identificadora para armazenar os fragmentos instanciados no bundle */
  private val BUNDLE_FRAGMENT = "BUNDLE_FRAGMENT"

  /** Chave identificadora para armazenar a pilha de fragmentos no bundle */
  private val BUNDLE_FRAGMENT_STACK = "BUNDLE_FRAGMENT_STACK"

  /** Chave identificadora para armazenar o índice do atual fragmento no bundle */
  private val BUNDLE_CURRENT_FRAGMENT_INDEX = "BUNDLE_CURRENT_FRAGMENT_INDEX"

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
  fun inject() {
  }

  /**
   * Configures View to be shown
   */
  fun setupComponents(savedInstanceState: Bundle?) {

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

  fun setupEvents() {
    bottom_navigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.navigation_artists  -> showFragment(mFragments[0], 0, null);
        R.id.navigation_songs    -> showFragment(mFragments[1], 1, null)
        R.id.navigation_playlist -> showFragment(mFragments[2], 2, null)
      }
      true
    }
  }

  fun showFragment(fragment: FragmentBase, fragmentIndex: Int, bundle: Bundle?) {

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
}
