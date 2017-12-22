package br.com.missao.cifrasus.database.entities

import br.com.missao.cifrasus.constants.Chord
import io.realm.RealmObject

/**
 * Chord's entity model
 */
open class ChordDB() : RealmObject() {

  var value: String
  var placement: Int

  init {
    this.value = ""
    this.placement = 0
  }

  constructor(chord: String, placement: Int) : this() {
    this.value = chord
    this.placement = placement
  }

  fun getChord(): Chord? {
    return Chord.getByValue(this.value)
  }

  fun setChord(chord: Chord) {
    this.value = chord.value
  }
}