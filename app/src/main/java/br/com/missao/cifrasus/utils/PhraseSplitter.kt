package br.com.missao.cifrasus.utils

import android.widget.TextView
import br.com.missao.cifrasus.extensions.getCharacterPixelAverage
import br.com.missao.cifrasus.extensions.getIntrinsicWidth
import br.com.missao.cifrasus.extensions.isAllTextDisplayed
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper

/**
 *  Helper methods to manipulate [PhraseWrapper]
 */
object PhraseSplitter {

  /**
   * Measures and splits a [phrase] in a [textView]
   */
  fun measurePhrase(textView: TextView, phrase: PhraseWrapper): List<PhraseWrapper> {
    val words = phrase.text.split("\\s".toRegex())
    val list = mutableListOf<PhraseWrapper>()
    val charSize = textView.getCharacterPixelAverage()

    var chords = phrase.chords
    var previous = ""
    var current = ""

    for (i in 0 until words.size) {
      current += "${words[i]} "

      if (!textView.isAllTextDisplayed(current, 50)) {

        val phraseWidth = textView.getIntrinsicWidth(previous)
        val splitIndex = shouldSplitAtIndex(chords, charSize = charSize, maxSize = phraseWidth)

        if (splitIndex != -1) {
          val decreasePlacement = phraseWidth / charSize
          list.add(PhraseWrapper(previous, chords.subList(0, splitIndex)))
          chords = updateChordsPlacement(decreasePlacement, chords.subList(splitIndex, chords.size))

        } else {
          list.add(PhraseWrapper(previous, chords))
          chords = mutableListOf()
        }

        current = "${words[i]} "
      }

      previous = current
    }

    if (!previous.isEmpty()) {
      list.add(PhraseWrapper(previous, chords))
    }

    return list
  }

  /**
   * Verifies if the list of chords needs to be split, since the phrase was. The chords will be split when
   * them reach [maxSize]. In the case that chords do not need to be split, the value -1 will be returned
   */
  private fun shouldSplitAtIndex(chords: List<ChordWrapper>, charSize: Int, maxSize: Int) : Int {
    var chordsWidth = 0

    for (index in 0 until chords.size) {

      // Most chords has length between 2, that why this 2 will be added
      chordsWidth += (chords[index].placement + 2) * charSize

      if (chordsWidth > maxSize) {
        return index
      }
    }

    return -1
  }

  /**
   * Updates Chords placement in [decrease], since they were split
   */
  private fun updateChordsPlacement(decrease: Int, chords: List<ChordWrapper>) : List<ChordWrapper> {
    val newChords = mutableListOf<ChordWrapper>()

    chords.forEach {
      var newPlacement = it.placement - decrease
      if (newPlacement < 0) {
        newPlacement = 0
      }
      newChords.add(ChordWrapper(it.value, newPlacement))
    }

    return newChords
  }
}