package com.example.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.core.ui.ItemListMovieAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.favoriteModule
import com.example.submission2.ui.fragment.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding?.rvFavorite?.visibility = View.GONE
            binding?.sflFavorite?.sfl?.visibility = View.VISIBLE
            binding?.emptyFavorite?.visibility = View.GONE
            binding?.tvFavorite?.visibility = View.GONE

            loadKoinModules(favoriteModule)

            val adapter = ItemListMovieAdapter()
            adapter.onItemClick = {
                replaceFragment(it.id)
            }

            favoriteViewModel.movieFavorite.observe(viewLifecycleOwner, { list ->
                if (list.isNotEmpty()) {
                    adapter.setData(list)
                    binding?.rvFavorite?.visibility = View.VISIBLE
                    binding?.sflFavorite?.sfl?.visibility = View.GONE
                    binding?.emptyFavorite?.visibility = View.GONE
                    binding?.tvFavorite?.visibility = View.GONE
                } else {
                    binding?.rvFavorite?.visibility = View.GONE
                    binding?.sflFavorite?.sfl?.visibility = View.GONE
                    binding?.emptyFavorite?.visibility = View.VISIBLE
                    binding?.tvFavorite?.visibility = View.VISIBLE
                }
            })

            LinearSnapHelper().attachToRecyclerView(binding?.rvFavorite)

            binding?.swipe?.setOnRefreshListener {
                binding?.rvFavorite?.visibility = View.GONE
                binding?.sflFavorite?.sfl?.visibility = View.VISIBLE
                binding?.emptyFavorite?.visibility = View.GONE
                binding?.tvFavorite?.visibility = View.GONE
                favoriteViewModel.loadItem()
                binding?.swipe?.isRefreshing = false
            }

            with(binding?.rvFavorite) {
                this?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    private fun replaceFragment(id: Int) {
        val detailFragment = DetailFragment()
        val bundle = Bundle()

        bundle.putInt(DetailFragment.extraId, id)
        detailFragment.arguments = bundle

        fragmentManager?.beginTransaction()?.apply {
            replace(com.example.submission2.R.id.navHostFragment, detailFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}