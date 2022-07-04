package com.sakal.mymusicapp.view.rv_viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.databinding.AudioItemBinding


class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val audioItemBinding = AudioItemBinding.bind(itemView)
    private val title = audioItemBinding.track
    private val poster = audioItemBinding.ava
    private val artist = audioItemBinding.artist

    fun bind(audio: Audio) {
        title.text = audio.track
        Glide.with(itemView)
            .load(audio.image)
            .centerCrop()
            .into(poster)
        artist.text = audio.artist
    }
}
