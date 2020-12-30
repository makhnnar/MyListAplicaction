package com.pedrogomez.mylistaplication.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedrogomez.mylistaplication.booklist.viewmodel.BookListViewModel
import com.pedrogomez.mylistaplication.databinding.ActivityBookListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class BookListActivity : AppCompatActivity() {

    private val bookListViewModel : BookListViewModel by viewModel()

    private lateinit var binding: ActivityBookListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bookListViewModel.getReposFromGitHub(
            "",
            0
        )
    }
}