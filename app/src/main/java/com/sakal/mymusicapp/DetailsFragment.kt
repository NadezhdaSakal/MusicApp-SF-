package com.sakal.mymusicapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_details.*
import android.content.Intent


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAudioDetails()
    }

    private fun setAudioDetails() {
        val audio = arguments?.get("audio") as Audio

        details_toolbar.title = audio.title
        details_ava.setImageResource(audio.ava)
        details_singer.text = audio.singer

    }
}
