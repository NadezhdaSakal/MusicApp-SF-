package com.sakal.mymusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {
    private lateinit var audioAdapter: AudioListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList: List<Audio> = emptyList()

        favorites_recycler.apply {
            audioAdapter = AudioListRecyclerAdapter(object : AudioListRecyclerAdapter.OnItemClickListener{
                override fun click(audio: Audio) {
                    (requireActivity() as MainActivity).launchDetailsFragment(audio)
                }
            })
            adapter = audioAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        audioAdapter.addItems(favoritesList)
    }
}
