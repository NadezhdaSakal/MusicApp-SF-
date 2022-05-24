package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.MainRepository


class Interactor(val repo: MainRepository) {
    fun getAudioDB(): List<Audio> = repo.audioDB
}

