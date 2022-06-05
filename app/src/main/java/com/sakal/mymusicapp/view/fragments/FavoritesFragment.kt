package com.sakal.mymusicapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sakal.mymusicapp.view.rv_adapters.AudioListRecyclerAdapter
import com.sakal.mymusicapp.view.rv_adapters.TopSpacingItemDecoration
import com.sakal.mymusicapp.databinding.FragmentFavoritesBinding
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.utils.AnimationHelper
import com.sakal.mymusicapp.view.MainActivity
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {
    private lateinit var audioAdapter: AudioListRecyclerAdapter
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList: List<Audio> = emptyList()

        AnimationHelper.performFragmentCircularRevealAnimation(favorites_fragment_root, requireActivity(),2)

        binding.favoritesRecycler.apply {
            audioAdapter = AudioListRecyclerAdapter(object : AudioListRecyclerAdapter.OnItemClickListener {
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
