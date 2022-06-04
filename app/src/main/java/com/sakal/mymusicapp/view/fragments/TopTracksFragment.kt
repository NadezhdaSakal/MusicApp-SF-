package com.sakal.mymusicapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sakal.mymusicapp.view.rv_adapters.TopSpacingItemDecoration
import com.sakal.mymusicapp.databinding.FragmentToptracksBinding
import com.sakal.mymusicapp.domain.Audio
import com.sakal.mymusicapp.utils.AnimationHelper
import com.sakal.mymusicapp.view.MainActivity
import com.sakal.mymusicapp.view.rv_adapters.AudioListRecyclerAdapter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_toptracks.*
import java.util.*


class TopTracksFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var audioAdapter: AudioListRecyclerAdapter
    private lateinit var binding: FragmentToptracksBinding
    private var audioDB = listOf<Audio>()
        set(value) {
            if (field == value) return
            field = value
            audioAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToptracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(toptracks, requireActivity(), 1)

        initSearchView()
        initRecyckler()

        viewModel.audioListLiveData.observe(viewLifecycleOwner, Observer<List<Audio>> {
            audioDB = it
        })

    }

    private fun initSearchView() {
        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    audioAdapter.addItems(audioDB)
                    return true
                }
                val result = audioDB.filter {
                    it.name.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                audioAdapter.addItems(result)
                return true
            }
        })
    }

    private fun initRecyckler() {
        tracks_recycler.apply {
            audioAdapter =
                AudioListRecyclerAdapter(object : AudioListRecyclerAdapter.OnItemClickListener {

                    override fun click(audio: Audio) {
                        (requireActivity() as MainActivity).launchDetailsFragment(audio)
                    }
                })
            adapter = audioAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}
