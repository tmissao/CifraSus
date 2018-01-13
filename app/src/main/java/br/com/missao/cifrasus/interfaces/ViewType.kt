package br.com.missao.cifrasus.interfaces

/**
 * Interface to work with multiple view types on RecyclerView
 */
interface ViewType {

  /**
   * Obtains view type delegate identifier
   */
  fun getViewType(): Int
}