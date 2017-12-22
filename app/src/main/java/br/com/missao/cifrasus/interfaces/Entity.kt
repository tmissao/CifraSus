package br.com.missao.cifrasus.interfaces

/**
 * Entity Interface
 */
interface Entity {

  companion object {
    val ID_KEY: String
      get() = "id"
  }

  /**
   * Entity unique key
   */
  var id: String?
}