package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable

/**
 * Contains Chord information as value and placement
 */
data class ChordWrapper(
    val value: String,
    val placement: Int
) : Parcelable {
  constructor(source: Parcel) : this(
      source.readString(),
      source.readInt()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeString(value)
    writeInt(placement)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<ChordWrapper> = object : Parcelable.Creator<ChordWrapper> {
      override fun createFromParcel(source: Parcel): ChordWrapper = ChordWrapper(source)
      override fun newArray(size: Int): Array<ChordWrapper?> = arrayOfNulls(size)
    }
  }
}