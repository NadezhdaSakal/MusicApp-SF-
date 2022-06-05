package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.Track
import com.sakal.mymusicapp.domain.Audio

object Converter {

    fun convertApiTrackListToDTOList(list: List<Track>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                artist=it.artist.name,
                track = it.name,
                image = it.image[1].size
            ))
        }
        return result
    }
}
