package com.sakal.mymusicapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sakal.mymusicapp.R
import com.sakal.mymusicapp.domain.Audio
import com.sakal.mymusicapp.view.fragments.DetailsFragment
import com.sakal.mymusicapp.view.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

    }

    fun launchDetailsFragment(audio: Audio) {
        val bundle = Bundle()
        bundle.putParcelable("audio", audio)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {
        topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id._latest -> {
                    Toast.makeText(this, "Поcледнее", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.playlist -> {
                    Toast.makeText(this, "Плэйлисты", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.play -> {
                    Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.next -> {
                    Toast.makeText(this, "Next audio", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.playback_order -> {
                    Toast.makeText(this, "Shuffle", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}




