package br.com.missao.cifrasus.model.wrappers

import android.os.Parcel
import android.os.Parcelable

/**
 * Constains Reddit News Information
 */
data class RedditNewsWrapper(
        val author: String,
        val title: String,
        val numComments: Int,
        val thumbnail: String,
        val url: String
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RedditNewsWrapper> = object : Parcelable.Creator<RedditNewsWrapper> {
            override fun createFromParcel(source: Parcel): RedditNewsWrapper = RedditNewsWrapper(source)
            override fun newArray(size: Int): Array<RedditNewsWrapper?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(author)
        dest.writeString(title)
        dest.writeInt(numComments)
        dest.writeString(thumbnail)
        dest.writeString(url)
    }
}