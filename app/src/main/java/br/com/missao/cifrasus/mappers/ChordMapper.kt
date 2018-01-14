package br.com.missao.cifrasus.mappers

import br.com.missao.cifrasus.database.entities.ChordDB
import br.com.missao.cifrasus.model.dtos.RequestChord
import br.com.missao.cifrasus.model.wrappers.ChordWrapper

/**
 * Chord's Mappers
 */
class ChordMapper {

  /**
   * Creates a [ChordWrapper] from [entity]
   */
  fun toWrapper(entity: ChordDB) = ChordWrapper(entity.value, entity.placement)

  /**
   * Creates a [RequestChord] from [url]
   */
  fun toChordRequestDTO(url: String) = RequestChord(url)
}