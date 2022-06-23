package com.sakal.mymusicapp.view.rv_adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.view.rv_viewholders.AudioViewHolder
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.databinding.AudioItemBinding
import kotlinx.android.synthetic.main.audio_item.view.*


class AudioListRecyclerAdapter:
PagingDataAdapter<Track, AudioListRecyclerAdapter.AudioViewHolder>(TracksComparator) {

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    object TracksComparator : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }
}
