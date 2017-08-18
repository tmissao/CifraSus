package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable

/**
 * Contains Phrase information as text sentence and chords
 */
data class PhraseWrapper(
        val text: String,
        val chords: List<ChordWrapper> = listOf()
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.createTypedArrayList(ChordWrapper.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(text)
        writeTypedList(chords)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PhraseWrapper> = object : Parcelable.Creator<PhraseWrapper> {
            override fun createFromParcel(source: Parcel): PhraseWrapper = PhraseWrapper(source)
            override fun newArray(size: Int): Array<PhraseWrapper?> = arrayOfNulls(size)
        }
    }
}