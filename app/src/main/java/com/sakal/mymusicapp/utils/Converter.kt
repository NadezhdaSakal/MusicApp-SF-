package com.sakal.mymusicapp.utils

import com.sakal.mymusicapp.data.Entity.PhishTrack
import com.sakal.mymusicapp.domain.Audio

object Converter {
    fun convertApiListToDTOList(list: List<PhishTrack>?): List<Audio> {
        val result = mutableListOf<Audio>()
        list?.forEach {
            result.add(Audio(
                id = it.id,
                title = it.title,
                ava = "",
                singer = "",
                isInFavorites = false,
            )
            )
        }
        return result
    }
}
