package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.Track
import com.sakal.mymusicapp.domain.Audio

object Converter {

   /* fun convertApiListToDTOList(list: List<Artist>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                artist=it.name,
                name = it.name,
                image = it.image[1].text,
            ))
        }
        return result
    }

    */

    fun convertApiTrackListToDTOList(list: List<Track>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                artist=it.name,
                name = it.name,
                image = it.image[0].size
            ))
        }
        return result
    }
}
