package br.com.missao.cifrasus.mappers

import br.com.missao.cifrasus.database.entities.Song
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongInfoWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper

/**
 * Song`s Mapper
 */
class SongMapper(val phraseMapper: PhraseMapper) {

  fun toWrapper(entity: Song): SongWrapper {
    val phrases: List<PhraseWrapper> =
        if (entity.phrases.size > 0) entity.phrases.map { phraseMapper.toWrapper(it) }
        else ArrayList()

    return SongWrapper(entity.id ?: "", entity.name, entity.artist, entity.getTone(),
        entity.getOriginalTone(), phrases)
  }

  fun toInfoWrapper(entity: Song) = SongInfoWrapper(entity.id!!, entity.name, entity.artist)

}