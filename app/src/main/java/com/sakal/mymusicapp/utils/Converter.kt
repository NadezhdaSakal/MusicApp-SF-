package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.Track
import com.sakal.mymusicapp.domain.Audio
import com.sakal.mymusicapp.data.Entity.Artist
import com.sakal.mymusicapp.data.Entity.Image

object Converter {

    fun convertApiTrackListToDTOList(list: List<Track>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                track = it.name,
                artist = it.artist.name,
                image = it.image[1].size
            ))
        }
        return result
    }
}
