package com.example.githubrepositoryapp.data.response

import com.example.githubrepositoryapp.data.entity.GithubRepoEntity

data class GithubRepoSearchResponse(
    val totalCount: Int,
    val items: List<GithubRepoEntity>
)
