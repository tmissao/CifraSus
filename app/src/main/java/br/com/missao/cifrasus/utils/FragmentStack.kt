package br.com.missao.cifrasus.utils

import br.com.missao.cifrasus.bases.FragmentBase
import java.io.Serializable
import java.util.*

/**
 * Classe utilitária para manter a pilha de [ {@link FragmentBase}] da aplicação.
 */
class FragmentStack : Serializable {

  /**
   * Identificador da Serialização.
   */
  companion object {
    @JvmField
    val serialVersionUID = 1L
  }

  /**
   * Pilha de Fragments.
   */
  val fragments = Vector<FragmentBase>()

  /**
   * Verifica que a pilha de [ [FragmentBase]] está vazia.

   * @return ` boolean `.
   */
  fun empty(): Boolean {
    return fragments.isEmpty()
  }

  /**
   * Retorna o [ [FragmentBase]] do topo da pilha sem remover este.

   * @return FragmentBase.
   */
  @Synchronized
  fun peek(): FragmentBase? {
    if (empty()) {
      return null
    }

    return fragments[fragments.count() - 1]
  }

  /**
   * Retorna o [ [FragmentBase]] do topo da pilha removendo este.

   * @return FragmentBase.
   */
  @Synchronized
  fun pop(): FragmentBase? {
    if (empty()) {
      return null
    }

    return fragments.removeAt(fragments.count() - 1)
  }

  /**
   * Inclui um [ [FragmentBase]] no topo da pilha.

   * @param f BaseFragment a ser incluido.
   * *
   * @return BaseFragment incluido.
   */
  fun push(f: FragmentBase): FragmentBase {
    if (fragments.contains(f)) {
      fragments.remove(f)
    }

    fragments.add(f)

    return f
  }

  /**
   * Obtêm um [ [FragmentBase]] da pilha utilizando sua TAG.

   * @param tag [ [String]] a ser utilizada na procura do fragment.
   * *
   * @return BaseFragment.
   */
  fun getFragment(tag: String): FragmentBase? {
    for (frag in fragments) {
      if (frag.getCustomTag() == tag) {
        return frag
      }
    }

    return null
  }

  /**
   * Obtêm um [ [FragmentBase]] limpando a pilha do topo até encontrar o fragment.

   * @param f Fragment a ser procurado.
   * *
   * @return BaseFragment.
   */
  fun pushAndClearTop(f: FragmentBase): FragmentBase {
    if (fragments.contains(f)) {
      for (i in fragments.count() - 1 downTo 0) {
        if (fragments[i] == f) {
          break
        } else {
          fragments.removeAt(i)
        }
      }
    } else {
      fragments.add(f)
    }

    return f
  }

  /**
   * Obtêm o tamanho da pilha de fragments.

   * @return ` int `.
   */
  fun size(): Int {
    return fragments.count()
  }

  /**
   * Obtêm o [ [FragmentBase]] da posição desejada.

   * @param position posição do fragment desejado.
   * *
   * @return BaseFragment.
   */
  operator fun get(position: Int): FragmentBase? {
    if (position < 0 || position >= size()) {
      return null
    }

    return fragments[position]
  }
}