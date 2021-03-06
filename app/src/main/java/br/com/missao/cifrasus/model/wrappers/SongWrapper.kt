package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable
import br.com.missao.cifrasus.constants.AdapterConstants
import br.com.missao.cifrasus.constants.Chord
import br.com.missao.cifrasus.interfaces.ViewType

/**
 * Constains Song information as Name, Artist, Tone and Chords
 */
data class SongWrapper(
    val id: String,
    val name: String,
    val artist: String,
    val tone: Chord,
    val originalTone: Chord,
    val phrases: List<PhraseWrapper>

) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      Chord.values()[parcel.readInt()],
      Chord.values()[parcel.readInt()],
      parcel.createTypedArrayList(PhraseWrapper.CREATOR))

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(name)
    parcel.writeString(artist)
    parcel.writeInt(tone.index)
    parcel.writeInt(originalTone.index)
    parcel.writeTypedList(phrases)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<SongWrapper> {
    override fun createFromParcel(parcel: Parcel): SongWrapper {
      return SongWrapper(parcel)
    }

    override fun newArray(size: Int): Array<SongWrapper?> {
      return arrayOfNulls(size)
    }
  }
}

/**
 * Constains Song minimum information as Name and Artist
 */
data class SongInfoWrapper(
    val id: String,
    val name: String,
    val artist: String

) : Parcelable, ViewType {

  override fun getViewType() = AdapterConstants.SONGS

  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString())

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(name)
    parcel.writeString(artist)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<SongInfoWrapper> {
    override fun createFromParcel(parcel: Parcel): SongInfoWrapper {
      return SongInfoWrapper(parcel)
    }

    override fun newArray(size: Int): Array<SongInfoWrapper?> {
      return arrayOfNulls(size)
    }
  }
}