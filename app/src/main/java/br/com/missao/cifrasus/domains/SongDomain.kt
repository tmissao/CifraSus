package br.com.missao.cifrasus.domains

import br.com.missao.cifrasus.bases.DomainBase
import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredPresenterOperations
import java.util.*

/**
 * Resolves [SongMvpModelOperations]
 */
class SongDomain : DomainBase<SongMvpRequiredPresenterOperations>(),
    SongMvpModelOperations {

  /**
   * Class's TAG
   */
  val TAG = SongDomain::class.java.simpleName

  override fun getSong(id: Long) {
    val name = "Smoke on the Water"
    val artist = "Deep Purple"
    val tone = Chord.F
    val phrases = mutableListOf(
        PhraseWrapper("We all came out to Montreux", mutableListOf(ChordWrapper("G5", 4))),
        PhraseWrapper("On the lake Geneva shoreline", mutableListOf(ChordWrapper("F5", 16), ChordWrapper("G5", 22))),
        PhraseWrapper("To make records with a mobile"),
        PhraseWrapper("We didn't have much time", mutableListOf(ChordWrapper("F5", 0), ChordWrapper("G5", 12))),
        PhraseWrapper("Frank Zappa & the Mothers"),
        PhraseWrapper("Were at the best place around", mutableListOf(ChordWrapper("F5", 20), ChordWrapper("G5", 26))),
        PhraseWrapper("But some stupid with a flare gun"),
        PhraseWrapper("Burned the place to the ground.", mutableListOf(ChordWrapper("F5", 16), ChordWrapper("G5", 24))),
        PhraseWrapper("\n"),
        PhraseWrapper("Smoke on the water,", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("Fire in the sky", mutableListOf(ChordWrapper("G5", 2))),
        PhraseWrapper("Smoke on the water", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("\n"),
        PhraseWrapper("They burned down the gambling house", mutableListOf(ChordWrapper("G5", 0))),
        PhraseWrapper("It died with an awful sound", mutableListOf(ChordWrapper("F5", 17), ChordWrapper("G5", 24))),
        PhraseWrapper("Funky & Claude was running in and out,"),
        PhraseWrapper("Pulling kids out of the ground", mutableListOf(ChordWrapper("F5", 0), ChordWrapper("G5", 10))),
        PhraseWrapper("When it all was over,"),
        PhraseWrapper("We had to find another place", mutableListOf(ChordWrapper("F5", 18), ChordWrapper("G5", 30))),
        PhraseWrapper("Swiss time was running out"),
        PhraseWrapper("It seemed that we would lose the race.", mutableListOf(ChordWrapper("F5", 26), ChordWrapper("G5", 40))),
        PhraseWrapper("\n"),
        PhraseWrapper("Smoke on the water,", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("Fire in the sky", mutableListOf(ChordWrapper("G5", 2))),
        PhraseWrapper("Smoke on the water", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("\n"),
        PhraseWrapper("We ended up at the Grand Hotel", mutableListOf(ChordWrapper("G5", 0))),
        PhraseWrapper("It was empty, cold and bare", mutableListOf(ChordWrapper("F5", 8), ChordWrapper("G5", 16))),
        PhraseWrapper("But with the Rolling Trucks Stones thing just outside,"),
        PhraseWrapper("We made our music there", mutableListOf(ChordWrapper("F5", 0), ChordWrapper("G5", 14))),
        PhraseWrapper("With a few red lights an' a few old beds,"),
        PhraseWrapper("We made a place to sweat", mutableListOf(ChordWrapper("F5", 13), ChordWrapper("G5", 22))),
        PhraseWrapper("No matter what we get out of this,"),
        PhraseWrapper("I know,   I know we'll never forget.", mutableListOf(ChordWrapper("F5", 8), ChordWrapper("G5", 25))),
        PhraseWrapper("\n"),
        PhraseWrapper("Smoke on the water,", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("Fire in the sky", mutableListOf(ChordWrapper("G5", 2))),
        PhraseWrapper("Smoke on the water", mutableListOf(ChordWrapper("C5", 3), ChordWrapper("Ab5", 15))),
        PhraseWrapper("\n")
    )

    val song = SongWrapper(name, artist, tone, phrases)
    presenter?.onGetSong(song)
  }


  /**
   * Updates song's tone according to the [degree]
   */
  override fun changeTone(song: SongWrapper, degree: Int) {
    val phrases = ArrayList<PhraseWrapper>()
    val tone = Chord.change(song.tone, degree = degree)
    song.phrases.forEach {
      val chords = ArrayList<ChordWrapper>()
      it.chords.forEach {
        chords.add(updateChordTone(it, degree = degree))
      }
      phrases.add(it.copy(chords = chords))
    }

    presenter?.onChangeTone(song.copy(phrases = phrases, tone = tone))
  }

  /**
   * Updates chord's tone according to the [degree]
   */
  private fun updateChordTone(wrapper: ChordWrapper, degree: Int) : ChordWrapper {
    val base = Chord.baseChord(wrapper.value)
    val variation = Chord.notBaseChord(wrapper.value)
    val updateChord = Chord.change(base, degree = degree)
    return wrapper.copy(value = updateChord.value + variation)
  }

  override fun setPresenter(presenter: SongMvpRequiredPresenterOperations) {
    super.presenter = presenter
  }
}