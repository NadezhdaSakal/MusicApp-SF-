package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.w3c.dom.Attr

@Parcelize
data class LastFMTrack(
    @SerializedName ("attrTrack")
    val attrTrack:Attr,
    @SerializedName("name")
    val name: String,
    @SerializedName("mbid")
    val artist: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("url")
    val url:String
    ) : Parcelable


