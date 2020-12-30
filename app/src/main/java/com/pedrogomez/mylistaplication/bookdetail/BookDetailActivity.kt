package com.pedrogomez.mylistaplication.bookdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedrogomez.mylistaplication.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}