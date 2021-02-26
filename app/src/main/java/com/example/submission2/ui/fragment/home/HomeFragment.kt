package com.example.submission2.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.ItemListPosterAdapter
import com.example.submission2.R
import com.example.submission2.databinding.FragmentHomeBinding
import com.example.submission2.ui.fragment.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val popularAdapter = ItemListPosterAdapter()
            popularAdapter.onItemClick = {
                replaceFragment(it.id)
            }

            val nowPlayingAdapter = ItemListPosterAdapter()
            nowPlayingAdapter.onItemClick = {
                replaceFragment(it.id)
            }

            val topRatedAdapter = ItemListPosterAdapter()
            topRatedAdapter.onItemClick = {
                replaceFragment(it.id)
            }

            val upcomingAdapter = ItemListPosterAdapter()
            upcomingAdapter.onItemClick = {
                replaceFragment(it.id)
            }

            homeViewModel.moviePopular.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            if (list.data != null) {

                            } else {
                                popularAdapter.setData(list.data)
                            }
                        }
                        is Resource.Error -> {

                        }
                    }
                } else {

                }
            })

            homeViewModel.movieNowPlaying.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            if (list.data != null) {

                            } else {
                                nowPlayingAdapter.setData(list.data)
                            }
                        }
                        is Resource.Error -> {

                        }
                    }
                } else {

                }
            })

            homeViewModel.movieTopRated.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            if (list.data != null) {

                            } else {
                                topRatedAdapter.setData(list.data)
                            }
                        }
                        is Resource.Error -> {

                        }
                    }
                } else {

                }
            })

            homeViewModel.movieUpcoming.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            if (list.data != null) {

                            } else {
                                upcomingAdapter.setData(list.data)
                            }
                        }
                        is Resource.Error -> {

                        }
                    }
                } else {

                }
            })

            binding?.swipe?.setOnRefreshListener {
                homeViewModel.loadItem()
                binding?.swipe?.isRefreshing = false
            }

            with(binding?.rvPopular) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = popularAdapter
            }

            with(binding?.rvNowPlaying) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = nowPlayingAdapter
            }

            with(binding?.rvTopRated) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = topRatedAdapter
            }

            with(binding?.rvUpcoming) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = upcomingAdapter
            }
        }
    }

    private fun replaceFragment(id: Int) {
        val detailFragment = DetailFragment()
        val bundle = Bundle()

        bundle.putInt(DetailFragment.extraId, id)
        detailFragment.arguments = bundle

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.navHostFragment, detailFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}