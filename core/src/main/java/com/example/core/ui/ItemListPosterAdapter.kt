package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.Config
import com.example.core.R
import com.example.core.databinding.ItemListPosterBinding
import com.example.core.domain.model.Movie

class ItemListPosterAdapter : RecyclerView.Adapter<ItemListPosterAdapter.Holder>() {
    private var list = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(list: List<Movie>?) {
        if (list == null) {
            return
        }
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListPosterBinding.bind(view)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Config.baseUrlImage + data.poster_path)
                    .error(R.drawable.ic_baseline_priority_high)
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_poster, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}