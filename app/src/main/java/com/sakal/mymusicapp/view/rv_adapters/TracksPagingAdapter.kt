package com.sakal.mymusicapp.view.rv_adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.data.entity.Tracks
import com.sakal.mymusicapp.view.rv_viewholders.AudioViewHolder

class TracksPagingAdapter :
        PagingDataAdapter<Tracks, TracksPagingAdapter>(TracksComparator) {

    object TracksComparator : DiffUtil.ItemCallback<Tracks>() {
            override fun areItemsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem == newItem
            }
        }
    }


