package br.com.missao.cifrasus.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import br.com.missao.cifrasus.R
import br.com.missao.cifrasus.bases.FragmentBase
import br.com.missao.cifrasus.extensions.setToolbar
import kotlinx.android.synthetic.main.home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

  /** Identificador da Classe */
  private val TAG = HomeActivity::class.java.name

  /** Chave identificadora para armazenar os fragmentos instanciados no bundle */
  private val BUNDLE_FRAGMENT = "BUNDLE_FRAGMENT"

  /** Fragmentos da aplicação */
  private lateinit var mFragments: LinkedList<FragmentBase>

  /**
   * Atual Fragmento da aplicacao
   */
  private var mCurrentFragment: FragmentBase? = null

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
    //showFragment(mFragments.first, null)
  }

  fun setupEvents() {
    bottom_navigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.navigation_artists  -> Log.d("navigation", "artist")
        R.id.navigation_songs    -> Log.d("navigation", "songs")
        R.id.navigation_playlist -> Log.d("navigation", "playlist")
      }
      true
    }
  }

  fun showFragment(fragment: FragmentBase, bundle: Bundle?) {

    var nextFragment = fragment
    val copy = supportFragmentManager.findFragmentByTag(fragment.getCustomTag())
    val ft = supportFragmentManager.beginTransaction()
    if (!mFragments.isEmpty()) {
      ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    if (copy != null) {
      nextFragment = copy as FragmentBase
    }

    if (bundle != null) {
      nextFragment.arguments = bundle
    }

    ft.replace(R.id.container, nextFragment, nextFragment.getCustomTag()).commit()

    mFragments.push(nextFragment)
    mCurrentFragment = nextFragment
  }

  /**
   * Create Fragments
   */
  private fun createFragments(): LinkedList<FragmentBase> {
    val fragments = LinkedList<FragmentBase>()
    //fragments.push(MapFragment())
    return fragments
  }
}
