package com.example.submission2.ui.fragment.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Resource
import com.example.core.ui.ItemListMovieAdapter
import com.example.core.ui.ItemListPosterAdapter
import com.example.submission2.R
import com.example.submission2.databinding.FragmentHomeBinding
import com.example.submission2.ui.fragment.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs
import kotlin.math.min


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
            binding?.btnSearch?.isEnabled = false

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

            binding?.tietSearch?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (p0 == null || p0.isEmpty()) {
                        binding?.rvSearch?.visibility = View.GONE
                        binding?.emptySearch?.cl?.visibility = View.GONE
                        binding?.errorSearch?.cl?.visibility = View.GONE
                        binding?.sflSearch?.sfl?.visibility = View.GONE
                        binding?.btnSearch?.isEnabled = false
                    } else {
                        homeViewModel.setQuery(p0.toString())
                        binding?.btnSearch?.isEnabled = true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding?.rvSearch?.visibility = View.GONE
                    binding?.emptySearch?.cl?.visibility = View.GONE
                    binding?.errorSearch?.cl?.visibility = View.GONE
                    binding?.sflSearch?.sfl?.visibility = View.VISIBLE
                    binding?.btnSearch?.isEnabled = false
                }
            })

            binding?.btnSearch?.setOnClickListener {
                homeViewModel.searchItem()
            }

            homeViewModel.moviePopular.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvPopular?.visibility = View.GONE
                            binding?.emptyPopular?.cl?.visibility = View.GONE
                            binding?.errorPopular?.cl?.visibility = View.GONE
                            binding?.sflPopular?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data == null) {
                                binding?.rvPopular?.visibility = View.GONE
                                binding?.emptyPopular?.cl?.visibility = View.VISIBLE
                                binding?.errorPopular?.cl?.visibility = View.GONE
                                binding?.sflPopular?.sfl?.visibility = View.GONE
                            } else {
                                popularAdapter.setData(list.data)
                                binding?.rvPopular?.visibility = View.VISIBLE
                                binding?.emptyPopular?.cl?.visibility = View.GONE
                                binding?.errorPopular?.cl?.visibility = View.GONE
                                binding?.sflPopular?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            when (val e = list.message) {
                                getString(R.string.remote_error) -> binding?.errorPopular?.tv?.text = e
                                else -> binding?.errorPopular?.tv?.text = getString(R.string.error)
                            }
                            binding?.rvPopular?.visibility = View.GONE
                            binding?.emptyPopular?.cl?.visibility = View.GONE
                            binding?.errorPopular?.cl?.visibility = View.VISIBLE
                            binding?.sflPopular?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.rvPopular?.visibility = View.GONE
                    binding?.emptyPopular?.cl?.visibility = View.VISIBLE
                    binding?.errorPopular?.cl?.visibility = View.GONE
                    binding?.sflPopular?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieNowPlaying.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvNowPlaying?.visibility = View.GONE
                            binding?.emptyNowPlaying?.cl?.visibility = View.GONE
                            binding?.errorNowPlaying?.cl?.visibility = View.GONE
                            binding?.sflNowPlaying?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data == null) {
                                binding?.rvNowPlaying?.visibility = View.GONE
                                binding?.emptyNowPlaying?.cl?.visibility = View.VISIBLE
                                binding?.errorNowPlaying?.cl?.visibility = View.GONE
                                binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                            } else {
                                nowPlayingAdapter.setData(list.data)
                                binding?.rvNowPlaying?.visibility = View.VISIBLE
                                binding?.emptyNowPlaying?.cl?.visibility = View.GONE
                                binding?.errorNowPlaying?.cl?.visibility = View.GONE
                                binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            when (val e = list.message) {
                                getString(R.string.remote_error) -> binding?.errorNowPlaying?.tv?.text = e
                                else -> binding?.errorNowPlaying?.tv?.text = getString(R.string.error)
                            }
                            binding?.rvNowPlaying?.visibility = View.GONE
                            binding?.emptyNowPlaying?.cl?.visibility = View.GONE
                            binding?.errorNowPlaying?.cl?.visibility = View.VISIBLE
                            binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.rvNowPlaying?.visibility = View.GONE
                    binding?.emptyNowPlaying?.cl?.visibility = View.VISIBLE
                    binding?.errorNowPlaying?.cl?.visibility = View.GONE
                    binding?.sflNowPlaying?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieTopRated.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvTopRated?.visibility = View.GONE
                            binding?.emptyTopRated?.cl?.visibility = View.GONE
                            binding?.errorTopRated?.cl?.visibility = View.GONE
                            binding?.sflTopRated?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data == null) {
                                binding?.rvTopRated?.visibility = View.GONE
                                binding?.emptyTopRated?.cl?.visibility = View.VISIBLE
                                binding?.errorTopRated?.cl?.visibility = View.GONE
                                binding?.sflTopRated?.sfl?.visibility = View.GONE
                            } else {
                                topRatedAdapter.setData(list.data)
                                binding?.rvTopRated?.visibility = View.VISIBLE
                                binding?.emptyTopRated?.cl?.visibility = View.GONE
                                binding?.errorTopRated?.cl?.visibility = View.GONE
                                binding?.sflTopRated?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            when (val e = list.message) {
                                getString(R.string.remote_error) -> binding?.errorTopRated?.tv?.text = e
                                else -> binding?.errorTopRated?.tv?.text = getString(R.string.error)
                            }
                            binding?.rvTopRated?.visibility = View.GONE
                            binding?.emptyTopRated?.cl?.visibility = View.GONE
                            binding?.errorTopRated?.cl?.visibility = View.VISIBLE
                            binding?.sflTopRated?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.rvTopRated?.visibility = View.GONE
                    binding?.emptyTopRated?.cl?.visibility = View.VISIBLE
                    binding?.errorTopRated?.cl?.visibility = View.GONE
                    binding?.sflTopRated?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieUpcoming.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvUpcoming?.visibility = View.GONE
                            binding?.emptyUpcoming?.cl?.visibility = View.GONE
                            binding?.errorUpcoming?.cl?.visibility = View.GONE
                            binding?.sflUpcoming?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data == null) {
                                binding?.rvUpcoming?.visibility = View.GONE
                                binding?.emptyUpcoming?.cl?.visibility = View.VISIBLE
                                binding?.errorUpcoming?.cl?.visibility = View.GONE
                                binding?.sflUpcoming?.sfl?.visibility = View.GONE
                            } else {
                                upcomingAdapter.setData(list.data)
                                binding?.rvUpcoming?.visibility = View.VISIBLE
                                binding?.emptyUpcoming?.cl?.visibility = View.GONE
                                binding?.errorUpcoming?.cl?.visibility = View.GONE
                                binding?.sflUpcoming?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            when (val e = list.message) {
                                getString(R.string.remote_error) -> binding?.errorUpcoming?.tv?.text = e
                                else -> binding?.errorUpcoming?.tv?.text = getString(R.string.error)
                            }
                            binding?.rvUpcoming?.visibility = View.GONE
                            binding?.emptyUpcoming?.cl?.visibility = View.GONE
                            binding?.errorUpcoming?.cl?.visibility = View.VISIBLE
                            binding?.sflUpcoming?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.rvUpcoming?.visibility = View.GONE
                    binding?.emptyUpcoming?.cl?.visibility = View.VISIBLE
                    binding?.errorUpcoming?.cl?.visibility = View.GONE
                    binding?.sflUpcoming?.sfl?.visibility = View.GONE
                }
            })

            homeViewModel.movieSearch.observe(viewLifecycleOwner, { list ->
                if (list != null) {
                    when (list) {
                        is Resource.Loading -> {
                            binding?.rvSearch?.visibility = View.GONE
                            binding?.emptySearch?.cl?.visibility = View.GONE
                            binding?.errorSearch?.cl?.visibility = View.GONE
                            binding?.sflSearch?.sfl?.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            if (list.data == null) {
                                binding?.rvSearch?.visibility = View.GONE
                                binding?.emptySearch?.cl?.visibility = View.VISIBLE
                                binding?.errorSearch?.cl?.visibility = View.GONE
                                binding?.sflSearch?.sfl?.visibility = View.GONE
                            } else {
                                upcomingAdapter.setData(list.data)
                                binding?.rvSearch?.visibility = View.VISIBLE
                                binding?.emptySearch?.cl?.visibility = View.GONE
                                binding?.errorSearch?.cl?.visibility = View.GONE
                                binding?.sflSearch?.sfl?.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            when (val e = list.message) {
                                getString(R.string.remote_error) -> binding?.errorSearch?.tv?.text = e
                                else -> binding?.errorSearch?.tv?.text = getString(R.string.error)
                            }
                            binding?.rvSearch?.visibility = View.GONE
                            binding?.emptySearch?.cl?.visibility = View.GONE
                            binding?.errorSearch?.cl?.visibility = View.VISIBLE
                            binding?.sflSearch?.sfl?.visibility = View.GONE
                        }
                    }
                } else {
                    binding?.rvSearch?.visibility = View.GONE
                    binding?.emptySearch?.cl?.visibility = View.GONE
                    binding?.errorSearch?.cl?.visibility = View.GONE
                    binding?.sflSearch?.sfl?.visibility = View.GONE
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

            with(binding?.rvSearch) {
                this?.layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
                    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
                        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
                        val mShrinkAmount = 0.5f
                        val mShrinkDistance = 0.75f

                        val midpoint = width / 2.0f
                        val d0 = 0.0f
                        val d1 = mShrinkDistance * midpoint
                        val s0 = 1.0f
                        val s1 = 1.0f - mShrinkAmount
                        for (i in 0 until childCount) {
                            val child = getChildAt(i)
                            if (child != null) {
                                val childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.0f
                                val d = min(d1, abs(midpoint - childMidpoint))
                                val scale = s0 + (s1-s0) * (d-d0) / (d1-d0)
                                child.scaleX = scale
                                child.scaleY = scale
                            }
                        }
                        return scrolled
                    }
                }
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