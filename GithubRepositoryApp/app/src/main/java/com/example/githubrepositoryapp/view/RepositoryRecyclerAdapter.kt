package com.example.githubrepositoryapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubrepositoryapp.data.entity.GithubRepoEntity
import com.example.githubrepositoryapp.databinding.ViewholderRepositoryItemBinding
import com.example.githubrepositoryapp.extensions.loadCenterInside

class RepositoryRecyclerAdapter(val RepositoryClickedListener: (GithubRepoEntity) -> Unit) : ListAdapter<GithubRepoEntity, RepositoryRecyclerAdapter.RepositoryItemViewHolder>
    (diffUtil) {
    inner class RepositoryItemViewHolder(private val binding: ViewholderRepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GithubRepoEntity)  = with(binding){
            ownerProfileImageView.loadCenterInside(data.owner.avatarUrl, 24f)
            ownerNameTextView.text = data.owner.login
            nameTextView.text = data.fullName
            subtextTextView.text = data.description
            stargazersCountText.text = data.stargazersCount.toString()
            data.language?.let {
                languageText.isGone = false
                languageText.text = it
            } ?: kotlin.run {
                languageText.isGone = true
                languageText.text = ""
            }

            binding.root.setOnClickListener {
                RepositoryClickedListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemViewHolder {
        return RepositoryItemViewHolder(ViewholderRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RepositoryItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<GithubRepoEntity>() {
            override fun areItemsTheSame(
                oldItem: GithubRepoEntity,
                newItem: GithubRepoEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: GithubRepoEntity,
                newItem: GithubRepoEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}