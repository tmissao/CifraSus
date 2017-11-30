package br.com.missao.cifrasus.constants

import java.util.*

/**
 * Represents chords value
 */
enum class Chord(val value: String, val index: Int) {



  C("C", 0), Csus("C#", 1), D("D", 2), Dsus("D#", 3), E("E", 4), F("F", 5), Fsus("F#", 6),
  G("G", 7), Gsus("G#", 8), A("A", 9), Asus("Bb", 10), B("B", 11);

  companion object {
    private val map: HashMap<String, Chord> = HashMap()
    private val array: Array<Chord> = arrayOf(C, Csus, D, Dsus, E, F, Fsus, G, Gsus, A, Asus, B)
    private val REGEX_CLEAN_CHORD = "(\\b[A-G|a-g]){1}([b#])*".toRegex()
    private val REGEX_NEGATE_CLEAN_CHORD = "[^A-G|a-g|#|b]+".toRegex()

    init {
      map.put("C", C)
      map.put("C#", Csus)
      map.put("DB", Csus)
      map.put("D", D)
      map.put("D#", Dsus)
      map.put("EB", Dsus)
      map.put("E", E)
      map.put("E#", F)
      map.put("FB", E)
      map.put("F", F)
      map.put("F#", Fsus)
      map.put("GB", Fsus)
      map.put("G", G)
      map.put("G#", Gsus)
      map.put("AB", Gsus)
      map.put("A", A)
      map.put("A#", Asus)
      map.put("BB", Asus)
      map.put("B", B)
      map.put("B#", C)
      map.put("CB", B)
    }

    /**
     * Obtains a [Chord] by its string value
     */
    fun getByValue(value: String) : Chord {
      return map[value.toUpperCase()] ?: throw IllegalArgumentException("Chord value $value not found")
    }

    /**
     * Changes chord increasing [degree]
     */
    fun change(chord: Chord, degree: Int) : Chord {
      val newTone = (chord.index + degree + array.size) % array.size
      return array[newTone]
    }

    /**
     * Transforms a complex chord like Am7 to its base chord A
     */
    fun baseChord(value: String) : Chord {
      val clean = REGEX_CLEAN_CHORD.find(value)?.groupValues?.get(0)
      return clean?.let { getByValue(it) } ?: throw IllegalArgumentException("Unable to clean Chord $value")
    }

    /**
     * Obtains the extra information (not base chord) of a chord. Ex Am7 -> 7
     */
    fun notBaseChord(value: String) : String? {
      val match = REGEX_NEGATE_CLEAN_CHORD.find(value)
      return match?.groupValues?.get(0) ?: String()
    }
  }

  /**
   * Calculates the difference of degrees between another [chord]
   */
  fun difference(chord: Chord) : Int {
    return chord.index - this.index
  }
}