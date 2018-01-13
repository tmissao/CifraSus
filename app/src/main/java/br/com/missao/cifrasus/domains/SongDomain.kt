package br.com.missao.cifrasus.domains

import br.com.missao.cifrasus.bases.DomainBase
import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.database.daos.SongDao
import br.com.missao.cifrasus.database.entities.ChordDB
import br.com.missao.cifrasus.database.entities.Phrase
import br.com.missao.cifrasus.database.entities.Song
import br.com.missao.cifrasus.interfaces.Logger
import br.com.missao.cifrasus.mappers.SongMapper
import br.com.missao.cifrasus.model.wrappers.ChordWrapper
import br.com.missao.cifrasus.model.wrappers.PhraseWrapper
import br.com.missao.cifrasus.model.wrappers.SongWrapper
import br.com.missao.cifrasus.mvps.SongMvpModelOperations
import br.com.missao.cifrasus.mvps.SongMvpRequiredPresenterOperations
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

/**
 * Resolves [SongMvpModelOperations]
 */
class SongDomain(val songDao: SongDao, val songMapper: SongMapper, val logger: Logger) :
    DomainBase<SongMvpRequiredPresenterOperations>(),
    SongMvpModelOperations {

  /**
   * Class's TAG
   */
  private val TAG = SongDomain::class.java.simpleName

  override fun getSong(id: String) {
    lateinit var realm: Realm

    Observable.defer {
      realm = Realm.getDefaultInstance()
      Observable.just(songDao.getById(realm, id))
    }
        .subscribeOn(Schedulers.io())
        .map(songMapper::toWrapper)
        .map {
          if (it.tone == it.originalTone) {
            it
          } else {
            updateSongTone(it, it.originalTone.difference(it.tone))
          }
        }
        .doOnTerminate { realm.close() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { presenter?.onGetSong(it) },
            { logger.e(TAG, it) }
        )

  }


  /**
   * Updates song's tone according to the [degree]
   */
  override fun changeTone(song: SongWrapper, degree: Int) {
    lateinit var realm: Realm

    val updatedSong = updateSongTone(song, degree)
    presenter?.onChangeTone(updatedSong)

    Observable.defer {
      realm = Realm.getDefaultInstance()
      Observable.just(songDao.getById(realm, song.id))
    }
        .subscribeOn(Schedulers.io())
        .doOnTerminate { realm.close() }
        .map { it.copy(tone = updatedSong.tone) }
        .subscribe(
            { songDao.save(realm, it) },
            { logger.e(TAG, it) }
        )
  }

  private fun updateSongTone(wrapper: SongWrapper, degree: Int): SongWrapper {
    val phrases = ArrayList<PhraseWrapper>()
    val tone = Chord.change(wrapper.originalTone, degree = degree)
    wrapper.phrases.forEach {
      val chords = ArrayList<ChordWrapper>()
      it.chords.forEach {
        chords.add(updateChordTone(it, degree = degree))
      }
      phrases.add(it.copy(chords = chords))
    }

    return wrapper.copy(phrases = phrases, tone = tone)
  }

  /**
   * Updates chord's tone according to the [degree]
   */
  private fun updateChordTone(wrapper: ChordWrapper, degree: Int): ChordWrapper {
    val base = Chord.baseChord(wrapper.value)
    val variation = Chord.notBaseChord(wrapper.value)
    val updateChord = Chord.change(base, degree = degree)
    return wrapper.copy(value = updateChord.value + variation)
  }

  private fun populateDatabase(): String {
    val song = Song("Smoke on the water", "Deep Purple", Chord.F, Chord.F)
    song.phrases = RealmList(
        with(Phrase("We all came out to Montreux")) {
          this.chords = RealmList(ChordDB("G5", 4))
          this
        },
        with(Phrase("On the lake Geneva shoreline")) {
          this.chords = RealmList(
              ChordDB("F5", 16),
              ChordDB("G5", 22)
          )
          this
        },
        Phrase("To make records with a mobile"),
        with(Phrase("We didn't have much time")) {
          this.chords = RealmList(ChordDB("G5", 4))
          this
        },
        Phrase("Frank Zappa & the Mothers"),
        with(Phrase("Were at the best place around")) {
          this.chords = RealmList(
              ChordDB("F5", 20),
              ChordDB("G5", 26)
          )
          this
        },
        Phrase("But some stupid with a flare gun"),
        with(Phrase("Burned the place to the ground.")) {
          this.chords = RealmList(
              ChordDB("F5", 16),
              ChordDB("G5", 24)
          )
          this
        },
        Phrase("\n"),
        with(Phrase("Smoke on the water,")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        with(Phrase("Fire in the sky")) {
          this.chords = RealmList(ChordDB("G5", 2))
          this
        },
        with(Phrase("Smoke on the water")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        Phrase("\n"),
        with(Phrase("They burned down the gambling house")) {
          this.chords = RealmList(ChordDB("G5", 0))
          this
        },
        with(Phrase("It died with an awful sound")) {
          this.chords = RealmList(
              ChordDB("F5", 17),
              ChordDB("G5", 24)
          )
          this
        },
        Phrase("Funky & Claude was running in and out,"),
        with(Phrase("Pulling kids out of the ground")) {
          this.chords = RealmList(
              ChordDB("F5", 0),
              ChordDB("G5", 10)
          )
          this
        },
        Phrase("When it all was over,"),
        with(Phrase("We had to find another place")) {
          this.chords = RealmList(
              ChordDB("F5", 18),
              ChordDB("G5", 30)
          )
          this
        },
        Phrase("Swiss time was running out"),
        with(Phrase("It seemed that we would lose the race.")) {
          this.chords = RealmList(
              ChordDB("F5", 26),
              ChordDB("G5", 40)
          )
          this
        },
        Phrase("\n"),
        with(Phrase("Smoke on the water,")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        with(Phrase("Fire in the sky")) {
          this.chords = RealmList(ChordDB("G5", 2))
          this
        },
        with(Phrase("Smoke on the water")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        Phrase("\n"),
        with(Phrase("We ended up at the Grand Hotel")) {
          this.chords = RealmList(ChordDB("G5", 0))
          this
        },
        with(Phrase("It was empty, cold and bare")) {
          this.chords = RealmList(
              ChordDB("F5", 8),
              ChordDB("G5", 16)
          )
          this
        },
        Phrase("But with the Rolling Trucks Stones thing just outside,"),
        with(Phrase("We made our music there")) {
          this.chords = RealmList(
              ChordDB("F5", 0),
              ChordDB("G5", 14)
          )
          this
        },
        Phrase("With a few red lights an' a few old beds,"),
        with(Phrase("We made a place to sweat")) {
          this.chords = RealmList(
              ChordDB("F5", 13),
              ChordDB("G5", 22)
          )
          this
        },
        Phrase("No matter what we get out of this,"),
        with(Phrase("I know,   I know we'll never forget.")) {
          this.chords = RealmList(
              ChordDB("F5", 8),
              ChordDB("G5", 25)
          )
          this
        },
        Phrase("\n"),
        with(Phrase("Smoke on the water,")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        with(Phrase("Fire in the sky")) {
          this.chords = RealmList(ChordDB("G5", 2))
          this
        },
        with(Phrase("Smoke on the water")) {
          this.chords = RealmList(
              ChordDB("C5", 3),
              ChordDB("Ab5", 15)
          )
          this
        },
        Phrase("\n")
    )

    songDao.save(Realm.getDefaultInstance(), song)
    return song.id ?: ""
  }

  override fun setPresenter(presenter: SongMvpRequiredPresenterOperations) {
    super.presenter = presenter
  }
}