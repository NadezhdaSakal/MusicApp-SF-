package com.sakal.mymusicapp.view.rv_adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Tracks
import com.sakal.mymusicapp.data.entity.TracksWrapper
import com.sakal.mymusicapp.view.rv_viewholders.AudioViewHolder
import kotlinx.android.synthetic.main.audio_item.view.*


class AudioListRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Audio>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AudioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.audio_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AudioViewHolder -> {
                holder.bind(items[position])
                holder.itemView.item_container.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }

    fun addItems(list: List<Audio>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    inner class TracksPagingAdapter :
        PagingDataAdapter<Tracks, TracksPagingAdapter>(com.sakal.mymusicapp.view.rv_adapters.TracksPagingAdapter.TracksComparator) {

        object TracksComparator : DiffUtil.ItemCallback<TracksWrapper>() {
        override fun areItemsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
            return oldItem == newItem
        }
    }



interface OnItemClickListener {
        fun click(audio: Audio)
    }
}
