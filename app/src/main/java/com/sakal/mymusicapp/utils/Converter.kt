package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.dto.LastFMTrack
import com.sakal.mymusicapp.domain.Audio

object Converter {

    fun convertApiListToDTOList(list: List<LastFMTrack>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                name = it.name,
                image = it.image,
                artist = it.artist,
                isInFavorites = false
            ))
        }
        return result
    }
}
