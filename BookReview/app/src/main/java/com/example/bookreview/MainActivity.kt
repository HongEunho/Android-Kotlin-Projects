package com.example.bookreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookreview.adapter.BookAdapter
import com.example.bookreview.api.BookService
import com.example.bookreview.databinding.ActivityMainBinding
import com.example.bookreview.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)
        bookService.getBestSellerBooks("3BC412ABBAFB33758DA345197E2E370607DC93D85B1D9E969A849D9B94010476")
                .enqueue(object: Callback<BestSellerDto> {
                    override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                        // TODO 성공처리
                        if (response.isSuccessful.not()) {
                            return
                        }

                        response.body()?.let {
                            Log.d(TAG, it.toString())

                            it.books.forEach { book ->
                                Log.d(TAG, book.toString())
                            }

                            adapter.submitList(it.books)
                        }
                    }

                    override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                        // TODO 실패처리
                    }

                })
    }

    fun initBookRecyclerView() {
        adapter = BookAdapter()

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}