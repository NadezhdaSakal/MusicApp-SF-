package com.sakal.mymusicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.audio_item.view.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setAudioDetails()
    }

    private fun setAudioDetails() {
        //Получаем наш фильм из переданного бандла
        var audio = intent.extras?.get("audio") as Audio

        details_ava.setImageResource(audio.ava)
        //details_singer.singer = audio.singer
        details_name.title = audio.title

    }
}
