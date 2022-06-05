package com.sakal.mymusicapp.view.rv_viewholders;

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.domain.Audio
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.audio_item.view.*

class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val track = itemView.track
    private val ava = itemView.ava
    private val artist = itemView.artist

    fun bind(audio: Audio) {
        track.text = audio.track
        Glide.with(itemView)
            .load(audio.image)
            .centerCrop()
            .into(ava)
        artist.text = audio.artist

    }
}
