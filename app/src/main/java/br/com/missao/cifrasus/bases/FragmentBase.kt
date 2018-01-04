package br.com.missao.cifrasus.bases

import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * Classe de controle interno para Fragments
 */
abstract class FragmentBase : Fragment(), Serializable {

  companion object {
    @JvmField
    val serialVersionUID = 1L
  }

  /**
   * Tag identificadora do [ {@link BaseFragment}].
   */
  protected open var TAG = FragmentBase::class.java.simpleName

  /**
   * Injeta os modulos de dependencia do fragmento
   */
  open protected fun injectDependencies() {
  }

  /**
   * Configura a fragment para o uso, preparando variavéis, eventos das views etc.
   */
  open protected fun setupComponents() {
  }

  /**
   * Configura os eventos do fragmento.
   */
  open protected fun setupEvents() {
  }

  /**
   * Carrega as variáveis do fragmento.
   */
  open protected fun loadFragment() {
  }

  /**
   * Evento de back pressed do fragmento.
   */
  open fun onBackPressed() {
    activity.finish()
  }

  /**
   * Obtêm a TAG identificador do fragmento.

   * @return [ [String]]
   */
  abstract fun getCustomTag(): String

  /**
   * Define a TAG identificador do fragmento.

   * @param tag [ [String]] de identificação.
   */
  fun setTag(tag: String) {
    TAG = tag
  }
}