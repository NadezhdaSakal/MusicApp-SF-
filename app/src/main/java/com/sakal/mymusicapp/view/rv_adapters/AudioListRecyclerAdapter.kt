import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Image
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.databinding.AudioItemBinding
import kotlinx.android.synthetic.main.audio_item.view.*


class AudioListRecyclerAdapter : PagingDataAdapter<Track, AudioViewHolder>(TrackDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AudioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.audio_item, parent, false))
    }


    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        holder.bind(getItem(position)?.image)
    }
}

class TrackDiffCallBack : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}

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

