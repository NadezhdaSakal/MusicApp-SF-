package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.domain.Audio

class MainRepository {
    val audioDB = listOf(
        Audio(R.drawable.ava1, "Rammstein", "Sonne"),
        Audio(R.drawable.ava2, "Мельница", "Ночная кобыла"),
        Audio(R.drawable.ava3, "Nightwish", "She is my sin"),
        Audio(R.drawable.ava4, "Louna", "Штурмуя небеса"),
        Audio(R.drawable.ava5, "Король и Шут", "Прыгну со скалы"),
        Audio(R.drawable.ava6, "Порнофильмы", "Я так соскучился"),
        Audio(R.drawable.ava7, "Ария", "Осколок льда"),
    )
}