package br.com.missao.cifrasus.mappers

import br.com.missao.cifrasus.database.entities.ChordDB
import br.com.missao.cifrasus.model.wrappers.ChordWrapper

/**
 * Chord's Mappers
 */
class ChordMapper {

  /**
   * Creates a [ChordWrapper] from [entity]
   */
  fun toWrapper(entity: ChordDB) = ChordWrapper(entity.value, entity.placement)
}