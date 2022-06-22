package com.sakal.mymusicapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "cached_tracks", indices = [Index(value = ["track"], unique = true)])

data class Audio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "track") val track: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "artist") val artist: String,
    var isInFavorites: Boolean = false
) : Parcelable
