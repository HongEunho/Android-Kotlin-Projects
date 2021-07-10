package com.example.githubrepositoryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositoryapp.data.entity.GithubRepoEntity
import com.example.githubrepositoryapp.databinding.ActivitySearchBinding
import com.example.githubrepositoryapp.utility.RetrofitUtil
import com.example.githubrepositoryapp.view.RepositoryRecyclerAdapter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: RepositoryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initViews()
        bindViews()
    }

    private fun initAdapter() = with(binding) {
        adapter = RepositoryRecyclerAdapter(RepositoryClickedListener = {
            Intent(this@SearchActivity, RepositoryActivity::class.java).apply {
                putExtra(RepositoryActivity.REPOSITORY_OWNER_KEY, it.owner.login)
                putExtra(RepositoryActivity.REPOSITORY_NAME_KEY, it.name)
            }
        })
    }

    private fun initViews() = with(binding) {
        emptyResultTextView.isGone = true
        recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
        recyclerView.adapter = adapter
    }

    private fun bindViews() = with(binding) {
        searchButton.setOnClickListener {
            searchKeyword(searchEditText.text.toString())
        }
    }

    private fun searchKeyword(keywordString: String) = launch {
        withContext(Dispatchers.IO) {
            val response = RetrofitUtil.githubApiService.searchRepositories(keywordString)
            if (response.isSuccessful) {
                val body = response.body()
                withContext(Dispatchers.Main) {
                    Log.e("response", body.toString())
                    if (body != null) {
                        setData(body.items)
                    }
                }
            }
        }
    }

    private fun setData(items: List<GithubRepoEntity>) {
        adapter.submitList(items)
    }
}