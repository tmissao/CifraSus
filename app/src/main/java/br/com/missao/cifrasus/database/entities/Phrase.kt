package br.com.missao.cifrasus.database.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Phrase's entity model
 */
open class Phrase() : RealmObject() {

  var text: String
  var chords: RealmList<ChordDB>


  init {
    this.text = ""
    this.chords = RealmList()
  }

  constructor(text: String) : this() {
    this.text = text
  }
}
