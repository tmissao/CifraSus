package br.com.missao.cifrasus.database.entities

import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.interfaces.Entity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Song`s entity model
 */
open class Song() : RealmObject(), Entity {

  @PrimaryKey override var id: String? = null
  var name: String
  var artist: String
  var originalToneValue: String
  var toneValue: String
  var lastView: Date
  var phrases: RealmList<Phrase>

  init {
    this.id = null
    this.name = ""
    this.artist = ""
    this.originalToneValue = ""
    this.toneValue = ""
    this.lastView = Date()
    this.phrases = RealmList()
  }

  constructor(name: String, artist: String, originalTone: Chord, tone: Chord,
      lastView: Date = Date(), id: String? = UUID.randomUUID().toString(),
      phrases: RealmList<Phrase> = RealmList()) : this() {
    this.id = id
    this.name = name
    this.artist = artist
    this.originalToneValue = originalTone.value
    this.toneValue = tone.value
    this.lastView = lastView
    this.phrases = phrases
  }

  fun copy(name: String = this.name, artist: String = this.artist,
      originalTone: Chord = this.getOriginalTone(), tone: Chord = this.getTone(),
      lastView: Date = this.lastView, id: String? = this.id, phrases: RealmList<Phrase> = this.phrases)
      = Song(name, artist, originalTone, tone, lastView, id, phrases)

  fun getOriginalTone(): Chord {
    return Chord.getByValue(this.originalToneValue)
  }

  fun setOriginalTone(tone: Chord) {
    this.originalToneValue = tone.value
  }

  fun getTone(): Chord {
    return Chord.getByValue(this.toneValue)
  }

  fun setTone(tone: Chord) {
    this.toneValue = tone.value
  }
}