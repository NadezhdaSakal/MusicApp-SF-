package com.sakal.mymusicapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sakal.mymusicapp.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sakal.mymusicapp.databinding.FragmentSettingsBinding
import com.sakal.mymusicapp.utils.AnimationHelper
import com.sakal.mymusicapp.viewmodel.SettingsFragmentViewModel

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(SettingsFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding.settingsFragmentRoot, requireActivity(), 5)
        viewModel.categoryPropertyLifeData.observe(viewLifecycleOwner, Observer<String> {
            when(it) {
                LISTENERS_CATEGORY -> binding.radioGroup.check(R.id.radio_listeners)
                PLAYCOUNT_CATEGORY -> binding.radioGroup.check(R.id.radio_playcount)
            }
        })
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radio_listeners -> viewModel.putCategoryProperty(LISTENERS_CATEGORY)
                R.id.radio_playcount -> viewModel.putCategoryProperty(PLAYCOUNT_CATEGORY)
            }
        }
    }

    companion object {
        private const val LISTENERS_CATEGORY = "listeners"
        private const val PLAYCOUNT_CATEGORY = "playcount"
    }
}
