package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable
import br.com.missao.cifrasus.constants.Chord

/**
 * Constains Song information as Name, Artist, Tone and Chords
 */
data class SongWrapper(
    val id: String,
    val name: String,
    val artist: String,
    val tone: Chord,
    val phrases: List<PhraseWrapper>

) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      Chord.values()[parcel.readInt()],
      parcel.createTypedArrayList(PhraseWrapper.CREATOR))

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(name)
    parcel.writeString(artist)
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