package com.sakal.mymusicapp.view.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.databinding.AudioItemBinding
import kotlinx.android.synthetic.main.audio_item.view.*
import javax.inject.Inject

class AudioListRecyclerAdapter @Inject constructor(param: TrackClickListener) :
    PagingDataAdapter<Audio, AudioListRecyclerAdapter.AudioViewHolder>(TrackComparator) {
    var trackClickListener: TrackClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AudioViewHolder(
            AudioItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun addItems(field: List<Audio>) {

    }

    inner class AudioViewHolder(private val binding: AudioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                trackClickListener?.onTrackClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as Track
                )
            }
        }

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


    object TrackComparator : DiffUtil.ItemCallback<Audio>() {
        override fun areItemsTheSame(oldItem: Audio, newItem: Audio): Boolean =
            oldItem.image == newItem.image

        override fun areContentsTheSame(oldItem: Audio, newItem: Audio): Boolean =
            oldItem == newItem
    }

    interface TrackClickListener {
        fun onTrackClicked(binding: AudioItemBinding, track: Track)
    }
}
