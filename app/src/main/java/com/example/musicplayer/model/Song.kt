/**
 * In Model Package we have created new data class Song and in data class we have defined val members along with datatype
 * and in end Parcelable and then we have implemented Parcelable methods.
 */

package com.example.musicplayer.model

import android.os.Parcel
import android.os.Parcelable

data class Song(
    val id: Long,
    val title: String?,
    val artist: String?,
    val duration: Long,
    val data: String?,
    val image: String?,



    ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(artist)
        parcel.writeValue(duration)
        parcel.writeString(data)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }

}
