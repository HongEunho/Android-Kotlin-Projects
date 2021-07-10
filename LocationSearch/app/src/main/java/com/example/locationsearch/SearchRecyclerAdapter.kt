package com.example.locationsearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.locationsearch.databinding.ItemResultBinding
import com.example.locationsearch.model.SearchResultEntity

class SearchRecyclerAdapter(val itemClickedListener: (SearchResultEntity) -> Unit): ListAdapter<SearchResultEntity, SearchRecyclerAdapter.SearchItemViewHolder>(diffUtil) {

    inner class SearchItemViewHolder(private val binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchResultEntity) {
            binding.titleTextView.text = data.name
            binding.subTitleTextView.text = data.fullAddress

            binding.root.setOnClickListener {
                itemClickedListener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<SearchResultEntity>(){
            override fun areItemsTheSame(oldItem: SearchResultEntity, newItem: SearchResultEntity): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: SearchResultEntity, newItem: SearchResultEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}