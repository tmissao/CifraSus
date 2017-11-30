package br.com.missao.cifrasus.domains

import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.SongMvpRequiredPresenterOperations
import br.com.missao.cifrasus.utils.ValuesGenerator
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Tests for class [SongDomain]
 */
class SongDomainTest {

  val domain = SongDomain()
  lateinit var presenter: SongMvpRequiredPresenterOperations

  @Before
  fun setUp() {
    presenter = mock()
    domain.setPresenter(presenter)
  }

  @Test
  fun getSong() {
    // TODO TEST
  }


  @Test
  fun changeTone() {
    val phrases1 = mutableListOf(
        PhraseWrapper("We all came out to Montreux", mutableListOf(ChordWrapper("G5", 4))),
        PhraseWrapper("On the lake Geneva shoreline", mutableListOf(ChordWrapper("F5", 16), ChordWrapper("G5", 22)))
    )

    val phrases2 = mutableListOf(
        PhraseWrapper("We all came out to Montreux", mutableListOf(ChordWrapper("G#m11", 4))),
        PhraseWrapper("On the lake Geneva shoreline", mutableListOf(ChordWrapper("F11", 16), ChordWrapper("GM5", 22)))
    )

    var input = createSong(phases = phrases1, tone = Chord.F)
    var input2 = createSong(phases = phrases2, tone = Chord.Fsus)

    domain.changeTone(input, degree = 2)

    argumentCaptor<SongWrapper>().apply {
      verify(presenter).onChangeTone(capture())
      val result = lastValue
      Assertions.assertThat(result.tone).isEqualTo(Chord.G)
      Assertions.assertThat(result.phrases[0].text).isEqualTo(phrases1[0].text)
      assertChord(result.phrases[0].chords[0], value = "A5", placement = 4)

      Assertions.assertThat(result.phrases[1].text).isEqualTo(phrases1[1].text)
      assertChord(result.phrases[1].chords[0], value = "G5", placement = 16)
      assertChord(result.phrases[1].chords[1], value = "A5", placement = 22)
    }

    domain.changeTone(input2, degree = 2)

    argumentCaptor<SongWrapper>().apply {
      verify(presenter, times(2)).onChangeTone(capture())
      val result = lastValue
      Assertions.assertThat(result.tone).isEqualTo(Chord.Gsus)
      Assertions.assertThat(result.phrases[0].text).isEqualTo(phrases2[0].text)
      assertChord(result.phrases[0].chords[0], value = "Bbm11", placement = 4)

      Assertions.assertThat(result.phrases[1].text).isEqualTo(phrases2[1].text)
      assertChord(result.phrases[1].chords[0], value = "G11", placement = 16)
      assertChord(result.phrases[1].chords[1], value = "AM5", placement = 22)
    }

    domain.changeTone(input, degree = -1)

    argumentCaptor<SongWrapper>().apply {
      verify(presenter, times(3)).onChangeTone(capture())
      val result = lastValue
      Assertions.assertThat(result.tone).isEqualTo(Chord.E)
      Assertions.assertThat(result.phrases[0].text).isEqualTo(phrases1[0].text)
      assertChord(result.phrases[0].chords[0], value = "F#5", placement = 4)

      Assertions.assertThat(result.phrases[1].text).isEqualTo(phrases1[1].text)
      assertChord(result.phrases[1].chords[0], value = "E5", placement = 16)
      assertChord(result.phrases[1].chords[1], value = "F#5", placement = 22)
    }

    domain.changeTone(input2, degree = -1)

    argumentCaptor<SongWrapper>().apply {
      verify(presenter, times(4)).onChangeTone(capture())
      val result = lastValue
      Assertions.assertThat(result.tone).isEqualTo(Chord.F)
      Assertions.assertThat(result.phrases[0].text).isEqualTo(phrases2[0].text)
      assertChord(result.phrases[0].chords[0], value = "Gm11", placement = 4)

      Assertions.assertThat(result.phrases[1].text).isEqualTo(phrases2[1].text)
      assertChord(result.phrases[1].chords[0], value = "E11", placement = 16)
      assertChord(result.phrases[1].chords[1], value = "F#M5", placement = 22)
    }
  }

  /**
   * Checks the chordwrapper info
   */
  private fun assertChord(chord: ChordWrapper, value: String, placement: Int) {
    Assertions.assertThat(chord.value).isEqualTo(value)
    Assertions.assertThat(chord.placement).isEqualTo(placement)
  }

  /**
   * Creates a [SongWrapper]
   */
  private fun createSong(name: String = ValuesGenerator.nonEmptyString(),
                         artist: String = ValuesGenerator.nonEmptyString(),
                         tone: Chord = ValuesGenerator.pick(Chord.values()),
                         phases: List<PhraseWrapper> = listOf()) : SongWrapper {
    return SongWrapper(name, artist, tone, phases)
  }

}