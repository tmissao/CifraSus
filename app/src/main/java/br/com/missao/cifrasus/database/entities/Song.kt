package br.com.missao.cifrasus.database.entities

import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.interfaces.Entity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Song`s entity model
 */
open class Song() : RealmObject(), Entity {

  @PrimaryKey override var id: String? = null
  var artist: String
  var originalToneValue: String
  var toneValue: String
  var lastView: Date

  init {
    this.id = null
    this.artist = ""
    this.originalToneValue = ""
    this.toneValue = ""
    this.lastView = Date()
  }

  constructor(artist: String, originalTone: Chord, tone: Chord,
      lastView: Date = Date(), id: String = UUID.randomUUID().toString()) : this() {
    this.id = id
    this.artist = artist
    this.originalToneValue = originalTone.value
    this.toneValue = tone.value
    this.lastView = lastView
  }

  fun getOriginalTone() : Chord? {
    return if (originalToneValue.length >= 0) Chord.getByValue(this.originalToneValue) else null
  }

  fun setOriginalTone(tone: Chord) {
    this.originalToneValue = tone.value
  }

  fun getTone() : Chord? {
    return if (toneValue.length >= 0) Chord.getByValue(this.toneValue) else null
  }

  fun setTone(tone: Chord) {
    this.toneValue = tone.value
  }
}