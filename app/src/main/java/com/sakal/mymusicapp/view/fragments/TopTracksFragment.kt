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
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.databinding.AudioItemBinding
import com.sakal.mymusicapp.utils.AnimationHelper
import com.sakal.mymusicapp.view.MainActivity
import com.sakal.mymusicapp.view.rv_adapters.AudioListRecyclerAdapter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_toptracks.*
import java.util.*
import android.widget.Toast
import androidx.core.view.isVisible
import com.sakal.mymusicapp.utils.AutoDisposable
import com.sakal.mymusicapp.utils.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit



class TopTracksFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private val autoDisposable = AutoDisposable()

    private lateinit var tracksAdapter: AudioListRecyclerAdapter
    private lateinit var binding: FragmentToptracksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
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

        AnimationHelper.performFragmentCircularRevealAnimation(binding.toptracks, requireActivity(), 1)

        initSearchView()
        initPullToRefresh()
        initRecyckler()

        viewModel.tracksListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                tracksAdapter.addItems(list)
            }
            .addTo(autoDisposable)
        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)
    }

    private fun initPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            tracksAdapter.items.clear()
            viewModel.getTracks()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            binding.searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    tracksAdapter.items.clear()
                    subscriber.onNext(newText)
                    return false
                }
                override fun onQueryTextSubmit(query: String): Boolean {
                    subscriber.onNext(query)
                    return false
                }
            })
        })
            .subscribeOn(Schedulers.io())
            .map {
                it.toLowerCase(Locale.getDefault()).trim()
            }
            .debounce(800, TimeUnit.MILLISECONDS)
            .filter {
                viewModel.getTracks()
                it.isNotBlank()
            }
            .flatMap {
                viewModel.getSearchResult(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                },
                onNext = {
                    tracksAdapter.addItems(it)
                }
            )
            .addTo(autoDisposable)
    }

    private fun initRecyckler() {
        binding.mainRecycler.apply {
            tracksAdapter =
                AudioListRecyclerAdapter(object : AudioListRecyclerAdapter.OnItemClickListener {
                    override fun click(audio: Audio) {
                        (requireActivity() as MainActivity).launchDetailsFragment(audio)
                    }
                })
            adapter = tracksAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}

