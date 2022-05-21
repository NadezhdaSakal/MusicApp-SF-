package com.sakal.mymusicapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var audioAdapter: AudioListRecyclerAdapter
    val audioDB = listOf(
        Audio(R.drawable.ava1, "Rammstein", "Sonne"),
        Audio(R.drawable.ava2, "Мельница", "Ночная кобыла"),
        Audio(R.drawable.ava3, "Nightwish", "She is my sin"),
        Audio(R.drawable.ava4, "Louna", "Штурмуя небеса"),
        Audio(R.drawable.ava5, "Король и Шут", "Прыгну со скалы"),
        Audio(R.drawable.ava6, "Порнофильмы", "Я так соскучился"),
        Audio(R.drawable.ava7, "Ария", "Осколок льда"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_recycler.apply {
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
        audioAdapter.addItems(audioDB)
    }

}