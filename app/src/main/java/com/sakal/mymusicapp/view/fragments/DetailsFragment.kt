package com.sakal.mymusicapp.view.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.databinding.FragmentDetailsBinding
import com.sakal.mymusicapp.view.notification.NotificationHelper

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
                "Check out this audio: ${audio.track} \n\n ${audio.artist}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        binding.detailsFabLatest.setOnClickListener {
            NotificationHelper.createNotification(requireContext(), audio)
        }

    }


    private fun setAudioDetails() {
        audio = arguments?.get("audio") as Audio

        Glide.with(this)
            .load(audio.image)
            .centerCrop()
            .into(binding.detailsAva)

        binding.detailsTrack.text = audio.track


        binding.detailsFabFavorites.setImageResource(
            if (audio.isInFavorites) R.drawable.ic_baseline_favorite
            else R.drawable.ic_baseline_favorite_border
        )
    }
}
