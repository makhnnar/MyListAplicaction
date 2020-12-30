package com.pedrogomez.mylistaplication.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.Item
import com.pedrogomez.mylistaplication.databinding.ActivityBookDetailBinding
import com.pedrogomez.mylistaplication.extensions.print

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    companion object {
        const val BOOK_DATA = "bookData"
    }

    private lateinit var bookData : Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try{
            bookData = intent.getSerializableExtra(BOOK_DATA) as Item
            "bookData: $bookData".print()
        }catch (e:Exception){
            "bookData: error".print()
        }
    }
}