package com.sakal.mymusicapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.domain.Audio
import android.content.Intent
import com.sakal.mymusicapp.databinding.FragmentDetailsBinding
import com.sakal.mymusicapp.data.ApiConstants
import com.bumptech.glide.Glide


    class DetailsFragment : Fragment() {
        private lateinit var audio: Audio
        private lateinit var binding: FragmentDetailsBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = FragmentDetailsBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setAudioDetails()

            binding.detailsFabFavorites.setOnClickListener {
                if (!audio.isInFavorites) {
                    binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite)
                    audio.isInFavorites = true
                } else {
                    binding.detailsFabFavorites.setImageResource(R.drawable.ic_baseline_favorite_border)
                    audio.isInFavorites = false
                }
            }

            binding.detailsFabShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Check out this audio: ${audio.title} \n\n ${audio.singer}"
                )
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share To:"))
            }
        }

        private fun setAudioDetails() {
            audio = arguments?.get("audio") as Audio

            binding.detailsToolbar.title = audio.title
            Glide.with(this)
                .load(ApiConstants.IMAGES_URL + "w100" +audio.ava)
                .centerCrop()
                .into(binding.detailsAva)

            binding.detailsSinger.text = audio.singer

            binding.detailsFabFavorites.setImageResource(
                if (audio.isInFavorites) R.drawable.ic_baseline_favorite
                else R.drawable.ic_baseline_favorite_border
            )
        }
    }
