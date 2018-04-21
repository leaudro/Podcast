package com.leaudro.podcast.data.model

import android.os.Parcel
import android.os.Parcelable

data class Podcast(val id: Long,
                   val title: String,
                   val subtitle: String,
                   val episodes: List<Episode>) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Episode.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
        writeString(subtitle)
        writeTypedList(episodes)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Podcast> = object : Parcelable.Creator<Podcast> {
            override fun createFromParcel(source: Parcel): Podcast = Podcast(source)
            override fun newArray(size: Int): Array<Podcast?> = arrayOfNulls(size)
        }
    }
}

data class Episode(val id: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Episode> = object : Parcelable.Creator<Episode> {
            override fun createFromParcel(source: Parcel): Episode = Episode(source)
            override fun newArray(size: Int): Array<Episode?> = arrayOfNulls(size)
        }
    }
}