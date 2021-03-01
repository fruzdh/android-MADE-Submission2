package com.example.submission2.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.core.data.Resource
import com.example.core.ui.ItemListMovieAdapter
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

            val searchAdapter = ItemListMovieAdapter()
            searchAdapter.onItemClick = {
                replaceFragment(it.id)
            }

            binding?.btnSearch?.setOnClickListener {
                if (!binding?.tietSearch?.text.isNullOrEmpty()) {
                    val text = binding?.tietSearch?.text.toString()
                    homeViewModel.setQuery(text)
                    homeViewModel.searchItem()
                }
            }

            homeViewModel.moviePopular.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvPopular?.visibility = View.GONE
                            binding?.errorPopular?.visibility = View.GONE
                            binding?.emptyPopular?.visibility = View.GONE
                            binding?.tvPopular?.visibility = View.GONE
                            binding?.sflPopular?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data.isNullOrEmpty()) {
                                binding?.emptyPopular?.playAnimation()
                                binding?.tvPopular?.text = getString(R.string.empty_data)
                                binding?.rvPopular?.visibility = View.GONE
                                binding?.errorPopular?.visibility = View.GONE
                                binding?.emptyPopular?.visibility = View.VISIBLE
                                binding?.tvPopular?.visibility = View.VISIBLE
                                binding?.sflPopular?.sfl?.visibility = View.GONE
                            } else {
                                popularAdapter.setData(list.data)
                                binding?.rvPopular?.visibility = View.VISIBLE
                                binding?.errorPopular?.visibility = View.GONE
                                binding?.emptyPopular?.visibility = View.GONE
                                binding?.tvPopular?.visibility = View.GONE
                                binding?.sflPopular?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding?.errorPopular?.playAnimation()
                            binding?.tvPopular?.text = getString(R.string.error)
                            binding?.rvPopular?.visibility = View.GONE
                            binding?.errorPopular?.visibility = View.VISIBLE
                            binding?.emptyPopular?.visibility = View.GONE
                            binding?.tvPopular?.visibility = View.VISIBLE
                            binding?.sflPopular?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.emptyPopular?.playAnimation()
                    binding?.tvPopular?.text = getString(R.string.empty_data)
                    binding?.rvPopular?.visibility = View.GONE
                    binding?.errorPopular?.visibility = View.GONE
                    binding?.emptyPopular?.visibility = View.VISIBLE
                    binding?.tvPopular?.visibility = View.VISIBLE
                    binding?.sflPopular?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieNowPlaying.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvNowPlaying?.visibility = View.GONE
                            binding?.errorNowPlaying?.visibility = View.GONE
                            binding?.emptyNowPlaying?.visibility = View.GONE
                            binding?.tvNowPlaying?.visibility = View.GONE
                            binding?.sflNowPlaying?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data.isNullOrEmpty()) {
                                binding?.emptyNowPlaying?.playAnimation()
                                binding?.tvNowPlaying?.text = getString(R.string.empty_data)
                                binding?.rvNowPlaying?.visibility = View.GONE
                                binding?.errorNowPlaying?.visibility = View.GONE
                                binding?.emptyNowPlaying?.visibility = View.VISIBLE
                                binding?.tvNowPlaying?.visibility = View.VISIBLE
                                binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                            } else {
                                nowPlayingAdapter.setData(list.data)
                                binding?.rvNowPlaying?.visibility = View.VISIBLE
                                binding?.errorNowPlaying?.visibility = View.GONE
                                binding?.emptyNowPlaying?.visibility = View.GONE
                                binding?.tvNowPlaying?.visibility = View.GONE
                                binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding?.errorNowPlaying?.playAnimation()
                            binding?.tvNowPlaying?.text = getString(R.string.error)
                            binding?.rvNowPlaying?.visibility = View.GONE
                            binding?.errorNowPlaying?.visibility = View.VISIBLE
                            binding?.emptyNowPlaying?.visibility = View.GONE
                            binding?.tvNowPlaying?.visibility = View.VISIBLE
                            binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.emptyNowPlaying?.playAnimation()
                    binding?.tvNowPlaying?.text = getString(R.string.empty_data)
                    binding?.rvNowPlaying?.visibility = View.GONE
                    binding?.errorNowPlaying?.visibility = View.GONE
                    binding?.emptyNowPlaying?.visibility = View.VISIBLE
                    binding?.tvNowPlaying?.visibility = View.VISIBLE
                    binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieTopRated.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvTopRated?.visibility = View.GONE
                            binding?.errorTopRated?.visibility = View.GONE
                            binding?.emptyTopRated?.visibility = View.GONE
                            binding?.tvTopRated?.visibility = View.GONE
                            binding?.sflTopRated?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data.isNullOrEmpty()) {
                                binding?.emptyTopRated?.playAnimation()
                                binding?.tvTopRated?.text = getString(R.string.empty_data)
                                binding?.rvTopRated?.visibility = View.GONE
                                binding?.errorTopRated?.visibility = View.GONE
                                binding?.emptyTopRated?.visibility = View.VISIBLE
                                binding?.tvTopRated?.visibility = View.VISIBLE
                                binding?.sflTopRated?.sfl?.visibility = View.GONE
                            } else {
                                topRatedAdapter.setData(list.data)
                                binding?.rvTopRated?.visibility = View.VISIBLE
                                binding?.errorTopRated?.visibility = View.GONE
                                binding?.emptyTopRated?.visibility = View.GONE
                                binding?.tvTopRated?.visibility = View.GONE
                                binding?.sflTopRated?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding?.errorTopRated?.playAnimation()
                            binding?.tvTopRated?.text = getString(R.string.error)
                            binding?.rvTopRated?.visibility = View.GONE
                            binding?.errorTopRated?.visibility = View.VISIBLE
                            binding?.emptyTopRated?.visibility = View.GONE
                            binding?.tvTopRated?.visibility = View.VISIBLE
                            binding?.sflTopRated?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.emptyTopRated?.playAnimation()
                    binding?.tvTopRated?.text = getString(R.string.empty_data)
                    binding?.rvTopRated?.visibility = View.GONE
                    binding?.errorTopRated?.visibility = View.GONE
                    binding?.emptyTopRated?.visibility = View.VISIBLE
                    binding?.tvTopRated?.visibility = View.VISIBLE
                    binding?.sflTopRated?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieUpcoming.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvUpcoming?.visibility = View.GONE
                            binding?.errorUpcoming?.visibility = View.GONE
                            binding?.emptyUpcoming?.visibility = View.GONE
                            binding?.tvUpcoming?.visibility = View.GONE
                            binding?.sflUpcoming?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data.isNullOrEmpty()) {
                                binding?.emptyUpcoming?.playAnimation()
                                binding?.tvUpcoming?.text = getString(R.string.empty_data)
                                binding?.rvUpcoming?.visibility = View.GONE
                                binding?.errorUpcoming?.visibility = View.GONE
                                binding?.emptyUpcoming?.visibility = View.VISIBLE
                                binding?.tvUpcoming?.visibility = View.VISIBLE
                                binding?.sflUpcoming?.sfl?.visibility = View.GONE
                            } else {
                                upcomingAdapter.setData(list.data)
                                binding?.rvUpcoming?.visibility = View.VISIBLE
                                binding?.errorUpcoming?.visibility = View.GONE
                                binding?.emptyUpcoming?.visibility = View.GONE
                                binding?.tvUpcoming?.visibility = View.GONE
                                binding?.sflUpcoming?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding?.errorUpcoming?.playAnimation()
                            binding?.tvUpcoming?.text = getString(R.string.error)
                            binding?.rvUpcoming?.visibility = View.GONE
                            binding?.errorUpcoming?.visibility = View.VISIBLE
                            binding?.emptyUpcoming?.visibility = View.GONE
                            binding?.tvUpcoming?.visibility = View.VISIBLE
                            binding?.sflUpcoming?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.emptyUpcoming?.playAnimation()
                    binding?.tvUpcoming?.text = getString(R.string.empty_data)
                    binding?.rvUpcoming?.visibility = View.GONE
                    binding?.errorUpcoming?.visibility = View.GONE
                    binding?.emptyUpcoming?.visibility = View.VISIBLE
                    binding?.tvUpcoming?.visibility = View.VISIBLE
                    binding?.sflUpcoming?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieSearch.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvSearch?.visibility = View.GONE
                            binding?.errorSearch?.visibility = View.GONE
                            binding?.emptySearch?.visibility = View.GONE
                            binding?.tvSearch?.visibility = View.GONE
                            binding?.sflSearch?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data.isNullOrEmpty()) {
                                binding?.emptySearch?.playAnimation()
                                binding?.tvSearch?.text = getString(R.string.empty_data)
                                binding?.rvSearch?.visibility = View.GONE
                                binding?.errorSearch?.visibility = View.GONE
                                binding?.emptySearch?.visibility = View.VISIBLE
                                binding?.tvSearch?.visibility = View.VISIBLE
                                binding?.sflSearch?.sfl?.visibility = View.GONE
                            } else {
                                searchAdapter.setData(list.data)
                                binding?.rvSearch?.visibility = View.VISIBLE
                                binding?.errorSearch?.visibility = View.GONE
                                binding?.emptySearch?.visibility = View.GONE
                                binding?.tvSearch?.visibility = View.GONE
                                binding?.sflSearch?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding?.errorSearch?.playAnimation()
                            binding?.tvSearch?.text = getString(R.string.error)
                            binding?.rvSearch?.visibility = View.GONE
                            binding?.errorSearch?.visibility = View.VISIBLE
                            binding?.emptySearch?.visibility = View.GONE
                            binding?.tvSearch?.visibility = View.VISIBLE
                            binding?.sflSearch?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.tvSearch?.text = getString(R.string.empty_data)
                    binding?.rvSearch?.visibility = View.GONE
                    binding?.errorSearch?.visibility = View.GONE
                    binding?.emptySearch?.visibility = View.GONE
                    binding?.tvSearch?.visibility = View.GONE
                    binding?.sflSearch?.sfl?.visibility = View.GONE
                }
            })

            LinearSnapHelper().attachToRecyclerView(binding?.rvPopular)
            LinearSnapHelper().attachToRecyclerView(binding?.rvNowPlaying)
            LinearSnapHelper().attachToRecyclerView(binding?.rvTopRated)
            LinearSnapHelper().attachToRecyclerView(binding?.rvUpcoming)
            LinearSnapHelper().attachToRecyclerView(binding?.rvSearch)

            binding?.swipe?.setOnRefreshListener {
                homeViewModel.loadItem()
                homeViewModel.searchItem()
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

            with(binding?.rvSearch) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = searchAdapter
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