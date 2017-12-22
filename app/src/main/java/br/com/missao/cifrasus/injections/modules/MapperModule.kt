package br.com.missao.cifrasus.injections.modules

import br.com.missao.cifrasus.mappers.ChordMapper
import br.com.missao.cifrasus.mappers.PhraseMapper
import br.com.missao.cifrasus.mappers.SongMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application's Mappers
 */
@Module
class MapperModule {

  /**
   * Provides [ChordMapper]
   */
  @Provides
  @Singleton
  fun providesChordMapper(): ChordMapper = ChordMapper()

  /**
   * Provides [PhraseMapper]
   */
  @Provides
  @Singleton
  fun providesPhrasesMapper(chordMapper: ChordMapper) = PhraseMapper(chordMapper)

  /**
   * Provides [SongMapper]
   */
  @Provides
  @Singleton
  fun providesSongMapper(phraseMapper: PhraseMapper) = SongMapper(phraseMapper)

}
