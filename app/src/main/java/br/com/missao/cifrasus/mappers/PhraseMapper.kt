package br.com.missao.cifrasus.mappers

import br.com.missao.cifrasus.database.entities.Phrase
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper

/**
 * Phrase`s Mapper
 */
class PhraseMapper(val chordMapper: ChordMapper) {

  /**
   * Creates [PhraseWrapper] from [entity]
   */
  fun toWrapper(entity: Phrase): PhraseWrapper {
    val chords: List<ChordWrapper> =
        if (entity.chords.size > 0) entity.chords.map { chordMapper.toWrapper(it) }
        else ArrayList()
    return PhraseWrapper(entity.text, chords)
  }
}