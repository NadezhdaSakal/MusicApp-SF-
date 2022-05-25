package com.sakal.mymusicapp.view.rv_viewholders;

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.domain.Audio
import kotlinx.android.synthetic.main.audio_item.view.*
import com.sakal.mymusicapp.data.ApiConstants
import com.bumptech.glide.Glide


class AudioViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
private val ava=itemView.ava
private val singer=itemView.singer
private val title=itemView.title
        fun bind(audio: Audio) {
                title.text = audio.title
                Glide.with(itemView)
                        .load(ApiConstants.IMAGES_URL + "w342" + audio.ava)
                        .centerCrop()
                        .into(ava)
                title.text=audio.title
        }
}
