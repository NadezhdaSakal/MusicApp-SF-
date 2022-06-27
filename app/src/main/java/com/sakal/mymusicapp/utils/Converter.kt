package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.data.entity.Audio

object Converter {

    fun convertApiTrackListToDTOList(list: List<Track>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(
                Audio(
                    track = it.name,
                    artist = it.artist?.name,
                    image = it.image[1]?.text,
                    isInFavorites = false

                )
            )

        }
        return result
    }


    }

