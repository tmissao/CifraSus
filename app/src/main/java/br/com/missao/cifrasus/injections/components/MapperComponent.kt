package br.com.missao.cifrasus.injections.components


import br.com.missao.cifrasus.injections.modules.MapperModule
import br.com.missao.cifrasus.mappers.ChordMapper
import br.com.missao.cifrasus.mappers.PhraseMapper
import br.com.missao.cifrasus.mappers.SongMapper
import dagger.Component
import javax.inject.Singleton

/**
 * Applications's Mapper Component
 */
@Singleton
@Component(modules = arrayOf(MapperModule::class))
interface MapperComponent {

  /**
   * Obtains [ChordMapper]
   */
  fun getChordMapper(): ChordMapper

  /**
   * Obtains [PhraseMapper]
   */
  fun getPhraseMapper(): PhraseMapper

  /**
   * Obtains [SongMapper]
   */
  fun getSongMapper(): SongMapper
}