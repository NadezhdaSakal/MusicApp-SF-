package com.sakal.mymusicapp.view.rv_viewholders;

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.domain.Audio
import kotlinx.android.synthetic.main.audio_item.view.*



class AudioViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
private var ava=itemView.ava
private var singer=itemView.singer
private var title=itemView.title


        fun bind(audio: Audio){
        ava.setImageResource(audio.ava)
        singer.text=audio.singer
        title.text=audio.title
        }
}
