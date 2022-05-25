package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.JamendoTrack
import com.sakal.mymusicapp.domain.Audio

object Converter {
    fun convertApiListToDTOList(list: List<JamendoTrack>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                title = it.title,
                ava = it.avaPath,
                singer = it.overview,
                isInFavorites = false
            )
            )
        }
        return result
    }
}
