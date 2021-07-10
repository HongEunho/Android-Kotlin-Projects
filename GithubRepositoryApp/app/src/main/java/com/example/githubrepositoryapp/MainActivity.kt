package com.example.githubrepositoryapp

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import com.example.githubrepositoryapp.data.database.DataBaseProvider
import com.example.githubrepositoryapp.data.entity.GithubOwner
import com.example.githubrepositoryapp.data.entity.GithubRepoEntity
import com.example.githubrepositoryapp.databinding.ActivityMainBinding
import com.example.githubrepositoryapp.view.RepositoryRecyclerAdapter
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val repositoryDao by lazy{
        DataBaseProvider.provideDB(applicationContext).repositoryDao()
    }

    private lateinit var repositoryRecyclerAdapter: RepositoryRecyclerAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        launch(coroutineContext) {
            loadLikeRepositoryList()
        }
    }

    private suspend fun loadLikeRepositoryList() = withContext(Dispatchers.IO) {
        val repoList = repositoryDao.getHistory()
        withContext(Dispatchers.Main) {
            setData(repoList)
        }
    }

    private fun initAdapter() {
        repositoryRecyclerAdapter = RepositoryRecyclerAdapter(RepositoryClickedListener = {
            startActivity(
                Intent(this, RepositoryActivity::class.java).apply {
                    putExtra(RepositoryActivity.REPOSITORY_OWNER_KEY, it.owner.login)
                    putExtra(RepositoryActivity.REPOSITORY_NAME_KEY, it.name)
                }
            )
        })
    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = repositoryRecyclerAdapter
        searchButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
    }

    private fun setData(githubRepositoryList: List<GithubRepoEntity>) = with(binding) {
        if (githubRepositoryList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            repositoryRecyclerAdapter.submitList(githubRepositoryList)
        }
    }
}