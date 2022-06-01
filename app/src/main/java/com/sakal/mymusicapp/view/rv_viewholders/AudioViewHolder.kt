package com.sakal.mymusicapp.view.rv_viewholders;

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.audio_item.view.*
import com.sakal.mymusicapp.data.ApiConstants
import com.bumptech.glide.Glide
import com.sakal.mymusicapp.domain.Audio


class AudioViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
private val ava=itemView.ava
private val artist=itemView.singer
private val title=itemView.title
        fun bind(audio: Audio) {
                title.text = audio.name
                Glide.with(itemView)
                        .load(ApiConstants.BASE_URL + "w342" + audio.image)
                        .centerCrop()
                        .into(ava)
                title.text=audio.name
        }
}
