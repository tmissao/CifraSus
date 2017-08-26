package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable

/**
 * Constains Song information as Name, Artist, Tone and Chords
 */
data class SongWrapper(
    val name: String,
    val artist: String,
    val tone: String,
    val phrases: List<PhraseWrapper>

) : Parcelable {
  constructor(source: Parcel) : this(
      source.readString(),
      source.readString(),
      source.readString(),
      source.createTypedArrayList(PhraseWrapper.CREATOR)
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeString(name)
    writeString(artist)
    writeString(tone)
    writeTypedList(phrases)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<SongWrapper> = object : Parcelable.Creator<SongWrapper> {
      override fun createFromParcel(source: Parcel): SongWrapper = SongWrapper(source)
      override fun newArray(size: Int): Array<SongWrapper?> = arrayOfNulls(size)
    }
  }
}