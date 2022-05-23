package com.sakal.mymusicapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sakal.mymusicapp.databinding.PlaylistsBinding
import com.sakal.mymusicapp.AnimationHelper
import kotlinx.android.synthetic.main.fragment_playlists.*

class PlaylistsFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(playlists_fragment_root, requireActivity(), 4)
    }
}




