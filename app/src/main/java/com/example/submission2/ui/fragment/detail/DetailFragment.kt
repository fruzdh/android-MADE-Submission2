package com.example.submission2.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.bumptech.glide.Glide
import com.example.core.Config
import com.example.core.data.Resource
import com.example.core.domain.model.MovieDetail
import com.example.core.ui.ItemListPosterAdapter
import com.example.submission2.R
import com.example.submission2.databinding.FragmentDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    companion object {
        const val extraId = "extraId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        activity?.findViewById<View>(R.id.btmNav)?.visibility = View.GONE
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null && arguments != null) {
            val id = arguments?.getInt(extraId)
            if (id != null) {
                detailViewModel.setId(id)
                detailViewModel.setItem()

                val recommendationsAdapter = ItemListPosterAdapter()
                recommendationsAdapter.onItemClick = {
                    replaceFragment(it.id)
                }

                val similarAdapter = ItemListPosterAdapter()
                similarAdapter.onItemClick = {
                    replaceFragment(it.id)
                }

                binding?.btnBack?.setOnClickListener {
                    activity?.onBackPressed()
                }

                binding?.swipe?.setOnRefreshListener {
                    detailViewModel.setItem()
                    binding?.swipe?.isRefreshing = false
                }

                val btmSht = activity!!.findViewById<View>(R.id.bottom_sheet)
                val bottomSheet = BottomSheetBehavior.from(btmSht)
                binding?.bottomSheet?.btnState?.setOnClickListener {
                    if (bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED) {
                        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                    } else {
                        bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }

                detailViewModel.movieDetail.observe(viewLifecycleOwner, { detail ->
                    if (detail != null) {
                        when (detail) {
                            is Resource.Loading -> {
                                detailVisibility(1)
                            }
                            is Resource.Success -> {
                                if (detail.data != null) {
                                    setData(detail.data!!)
                                    detailVisibility(2)

                                    detailViewModel.movieRecommendations.observe(viewLifecycleOwner, { list ->
                                        if (list != null) {
                                            when (list) {
                                                is Resource.Loading -> {
                                                    binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.emptyRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.tvRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.VISIBLE
                                                }
                                                is Resource.Success -> {
                                                    if (list.data.isNullOrEmpty()) {
                                                        binding?.bottomSheet?.tvRecommendations?.text = getString(R.string.empty_data)
                                                        binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                                                        binding?.bottomSheet?.emptyRecommendations?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                                                        binding?.bottomSheet?.tvRecommendations?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.GONE
                                                    } else {
                                                        recommendationsAdapter.setData(list.data)
                                                        binding?.bottomSheet?.rvRecommendations?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.emptyRecommendations?.visibility = View.GONE
                                                        binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                                                        binding?.bottomSheet?.tvRecommendations?.visibility = View.GONE
                                                        binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.GONE
                                                    }
                                                }
                                                is Resource.Error -> {
                                                    binding?.bottomSheet?.tvRecommendations?.text = getString(R.string.error)
                                                    binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.emptyRecommendations?.visibility = View.GONE
                                                    binding?.bottomSheet?.errorRecommendations?.visibility = View.VISIBLE
                                                    binding?.bottomSheet?.tvRecommendations?.visibility = View.VISIBLE
                                                    binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.GONE
                                                }
                                            }
                                        } else {
                                            binding?.bottomSheet?.tvRecommendations?.text = getString(R.string.empty_data)
                                            binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                                            binding?.bottomSheet?.emptyRecommendations?.visibility = View.VISIBLE
                                            binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                                            binding?.bottomSheet?.tvRecommendations?.visibility = View.VISIBLE
                                            binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.GONE
                                        }
                                    })

                                    detailViewModel.movieSimilar.observe(viewLifecycleOwner, { list ->
                                        if (list != null) {
                                            when (list) {
                                                is Resource.Loading -> {
                                                    binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.emptySimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.tvSimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.VISIBLE
                                                }
                                                is Resource.Success -> {
                                                    if (list.data.isNullOrEmpty()) {
                                                        binding?.bottomSheet?.tvSimilar?.text = getString(R.string.empty_data)
                                                        binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                                                        binding?.bottomSheet?.emptySimilar?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                                                        binding?.bottomSheet?.tvSimilar?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.GONE
                                                    } else {
                                                        similarAdapter.setData(list.data)
                                                        binding?.bottomSheet?.rvSimilar?.visibility = View.VISIBLE
                                                        binding?.bottomSheet?.emptySimilar?.visibility = View.GONE
                                                        binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                                                        binding?.bottomSheet?.tvSimilar?.visibility = View.GONE
                                                        binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.GONE
                                                    }
                                                }
                                                is Resource.Error -> {
                                                    binding?.bottomSheet?.tvSimilar?.text = getString(R.string.error)
                                                    binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.emptySimilar?.visibility = View.GONE
                                                    binding?.bottomSheet?.errorSimilar?.visibility = View.VISIBLE
                                                    binding?.bottomSheet?.tvSimilar?.visibility = View.VISIBLE
                                                    binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.GONE
                                                }
                                            }
                                        } else {
                                            binding?.bottomSheet?.tvSimilar?.text = getString(R.string.empty_data)
                                            binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                                            binding?.bottomSheet?.emptySimilar?.visibility = View.VISIBLE
                                            binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                                            binding?.bottomSheet?.tvSimilar?.visibility = View.VISIBLE
                                            binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.GONE
                                        }
                                    })
                                } else {
                                    detailVisibility(3)
                                }
                            }
                            is Resource.Error -> {
                                detailVisibility(4)
                            }
                        }
                    } else {
                        detailVisibility(3)
                    }
                })

                LinearSnapHelper().attachToRecyclerView(binding?.bottomSheet?.rvRecommendations)
                LinearSnapHelper().attachToRecyclerView(binding?.bottomSheet?.rvSimilar)

                with(binding?.bottomSheet?.rvRecommendations) {
                    this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    this?.setHasFixedSize(true)
                    this?.adapter = recommendationsAdapter
                }

                with(binding?.bottomSheet?.rvSimilar) {
                    this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    this?.setHasFixedSize(true)
                    this?.adapter = similarAdapter
                }
            } else {
                detailVisibility(3)
            }
        }
    }

    private fun setData(detail: MovieDetail) {
        binding?.ivPoster?.let { iv ->
            Glide.with(iv)
                    .load(Config.baseUrlImage + detail.poster_path)
                    .error(com.example.core.R.drawable.ic_baseline_priority_high)
                    .into(iv)
        }

        binding?.bottomSheet?.tvTitle?.text = detail.title
        binding?.bottomSheet?.tvTagline?.text = detail.tagline
        binding?.bottomSheet?.tvDate?.text = detail.release_date
        binding?.bottomSheet?.tvGenre?.text = detail.genres
        binding?.bottomSheet?.tvRuntime?.text = getString(R.string.minutes, detail.runtime)
        binding?.bottomSheet?.tvOverview?.text = detail.overview

        val rate = (detail.vote_average * 10).toInt()
        binding?.tvVote?.text = rate.toString()
        binding?.progressBar?.progress = rate

        var favorite = detail.favorite
        setFavorite(favorite)
        binding?.btnFavorite?.setOnClickListener {
            favorite = !favorite
            detailViewModel.updateMovieDetail(detail, favorite)
            setFavorite(favorite)
        }
    }

    private fun setFavorite(favorite: Boolean) {
        if (favorite) {
            binding?.btnFavorite?.text = getString(R.string.remove_to_favorite)
        } else {
            binding?.btnFavorite?.text = getString(R.string.add_to_favorite)
        }
    }

    private fun detailVisibility(type: Int) {
        binding?.ivPoster?.visibility = View.GONE
        binding?.cl?.visibility = View.GONE
        binding?.cardView2?.visibility = View.GONE
        binding?.progressBar?.visibility = View.GONE
        binding?.tvVote?.visibility = View.GONE
        binding?.sflImage?.visibility = View.GONE
        binding?.errorDetail?.visibility = View.GONE
        binding?.emptyDetail?.visibility = View.GONE
        binding?.tvDetail?.visibility = View.GONE
        when (type) {
            1 -> {
                binding?.cl?.visibility = View.VISIBLE
                binding?.cardView2?.visibility = View.VISIBLE
                binding?.progressBar?.visibility = View.VISIBLE
                binding?.tvVote?.visibility = View.VISIBLE
                binding?.sflImage?.visibility = View.VISIBLE

                binding?.bottomSheet?.tvTitle?.visibility = View.GONE
                binding?.bottomSheet?.tvTagline?.visibility = View.GONE
                binding?.bottomSheet?.tvDate?.visibility = View.GONE
                binding?.bottomSheet?.tvGenre?.visibility = View.GONE
                binding?.bottomSheet?.tvRuntime?.visibility = View.GONE
                binding?.bottomSheet?.tvOverview?.visibility = View.GONE
                binding?.bottomSheet?.sflText?.visibility = View.VISIBLE
                binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.emptyRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.tvRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.VISIBLE
                binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                binding?.bottomSheet?.emptySimilar?.visibility = View.GONE
                binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                binding?.bottomSheet?.tvSimilar?.visibility = View.GONE
                binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.VISIBLE
            }
            2 -> {
                binding?.ivPoster?.visibility = View.VISIBLE
                binding?.cl?.visibility = View.VISIBLE
                binding?.cardView2?.visibility = View.VISIBLE
                binding?.progressBar?.visibility = View.VISIBLE
                binding?.tvVote?.visibility = View.VISIBLE

                binding?.bottomSheet?.tvTitle?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvTagline?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvDate?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvGenre?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvRuntime?.visibility = View.VISIBLE
                binding?.bottomSheet?.tvOverview?.visibility = View.VISIBLE
                binding?.bottomSheet?.sflText?.visibility = View.GONE
                binding?.bottomSheet?.rvRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.emptyRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.errorRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.tvRecommendations?.visibility = View.GONE
                binding?.bottomSheet?.sflRecommendations?.sfl?.visibility = View.VISIBLE
                binding?.bottomSheet?.rvSimilar?.visibility = View.GONE
                binding?.bottomSheet?.emptySimilar?.visibility = View.GONE
                binding?.bottomSheet?.errorSimilar?.visibility = View.GONE
                binding?.bottomSheet?.tvSimilar?.visibility = View.GONE
                binding?.bottomSheet?.sflSimilar?.sfl?.visibility = View.VISIBLE
            }
            3 -> {
                binding?.tvDetail?.text = getString(R.string.empty_data)
                binding?.emptyDetail?.visibility = View.VISIBLE
                binding?.tvDetail?.visibility = View.VISIBLE
            }
            4 -> {
                binding?.tvDetail?.text = getString(R.string.error)
                binding?.errorDetail?.visibility = View.VISIBLE
                binding?.tvDetail?.visibility = View.VISIBLE
            }
        }
    }

    private fun replaceFragment(id: Int) {
        val detailFragment = DetailFragment()
        val bundle = Bundle()

        bundle.putInt(extraId, id)
        detailFragment.arguments = bundle

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.navHostFragment, detailFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.findViewById<View>(R.id.btmNav)?.visibility = View.VISIBLE
        _binding = null
    }

}