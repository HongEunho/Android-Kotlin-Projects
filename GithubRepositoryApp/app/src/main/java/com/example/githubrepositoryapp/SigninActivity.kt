package com.example.githubrepositoryapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isGone
import com.example.githubrepositoryapp.databinding.ActivityMainBinding
import com.example.githubrepositoryapp.databinding.ActivitySigninBinding
import com.example.githubrepositoryapp.utility.AuthTokenProvider
import com.example.githubrepositoryapp.utility.RetrofitUtil
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SigninActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivitySigninBinding

    private val authTokenProvider by lazy{
        AuthTokenProvider(this)
    }

    val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkAuthCodeExist()) {
            launchMainActivity()
        } else{
            initViews()
        }
    }

    private fun initViews() = with(binding){
        loginButton.setOnClickListener {
            loginGithub()
        }
    }

    private fun launchMainActivity() {

    }

    private fun checkAuthCodeExist() : Boolean{
        return authTokenProvider.token.isNullOrEmpty().not()
    }

    private fun loginGithub() {
        val loginUri = Uri.Builder().scheme("https").authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("cliend_id", BuildConfig.GITHUB_CLIEND_ID)
            .build()

        CustomTabsIntent.Builder().build().also{
            it.launchUrl(this, loginUri)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.getQueryParameter("code")?.let{
            // todo getAccessToken
            launch(coroutineContext) {
                showProgress()
                val getAccessTokenJob = getAccessToken(it)
                dismissProgress()
            }
        }
    }

    private suspend fun showProgress() = withContext(coroutineContext) {
        with(binding) {
            loginButton.isGone = true
            progressBar.isGone = false
            progressTextView.isGone = false
        }
    }

    private suspend fun dismissProgress() = withContext(coroutineContext) {
        with(binding) {
            loginButton.isGone = false
            progressBar.isGone = true
            progressTextView.isGone = true
        }
    }

    private suspend fun getAccessToken(code: String) = withContext(Dispatchers.IO){
        val response = RetrofitUtil.authApiService.getAccessToken(
            clientId = BuildConfig.GITHUB_CLIEND_ID,
            clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
            code = code
        )

        if (response.isSuccessful) {
            val accessToken = response.body()?.accessToken ?: ""
            Log.e("accessToken", accessToken)
            if (accessToken.isNotEmpty()) {
                authTokenProvider.updateToken(accessToken)
            } else {
                Toast.makeText(this@SigninActivity, "accessToken이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}