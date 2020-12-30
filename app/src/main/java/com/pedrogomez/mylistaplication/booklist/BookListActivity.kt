package com.pedrogomez.mylistaplication.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedrogomez.mylistaplication.databinding.ActivityBookListBinding

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}