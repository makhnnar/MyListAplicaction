package com.pedrogomez.mylistaplication.booklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrogomez.mylistaplication.bookdetail.BookDetailActivity
import com.pedrogomez.mylistaplication.booklist.adapter.BookViewHolder
import com.pedrogomez.mylistaplication.booklist.adapter.BooksAdapter
import com.pedrogomez.mylistaplication.booklist.models.adapters.BookDataAdapter
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.Item
import com.pedrogomez.mylistaplication.booklist.viewmodel.BookListViewModel
import com.pedrogomez.mylistaplication.databinding.ActivityBookListBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.pedrogomez.mylistaplication.booklist.models.result.Result
import com.pedrogomez.mylistaplication.extensions.remove
import com.pedrogomez.mylistaplication.extensions.show
import kotlinx.serialization.json.Json

class BookListActivity : AppCompatActivity(),
    BookViewHolder.OnClickItemListener{

    private val bookListViewModel : BookListViewModel by viewModel()

    private lateinit var binding: ActivityBookListBinding

    private lateinit var booksAdapter : BooksAdapter

    private var counter : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initObservers()
        binding.etSearchField.addTextChangedListener {
            if(counter!=null){
                counter?.cancel()
            }
            counter = object : CountDownTimer(500,100){
                override fun onTick(millisUntilFinished: Long) {

                }
                /**
                 * Este contador se ejecuta para llamar al endpoint si y solo si el usario
                 * dejo de teclear
                 * */
                override fun onFinish() {
                    booksAdapter.clearData()
                    bookListViewModel.getReposFromGitHub(
                        it.toString(),
                        0
                    )
                }

            }.start()
        }
    }

    private fun initRecyclerView() {
        booksAdapter = BooksAdapter(this@BookListActivity)
        binding.rvBookItems.apply{
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(this@BookListActivity)
        }
    }

    private fun initObservers(){
        bookListViewModel.observeData().observe(
            this,
            Observer {
                when(it){
                    is Result.Success ->{
                        binding.pbBooksLoading.remove()
                        booksAdapter.setData(
                            it.data.items
                        )
                    }
                    is Result.Loading -> {
                        binding.pbBooksLoading.show()
                    }
                    is Result.Error -> {
                        binding.pbBooksLoading.remove()
                    }
                }
            }
        )
    }

    override fun goToBookDetail(data: Item) {
        val intent = Intent(
            this,
            BookDetailActivity::class.java
        )
        intent.putExtra(
            BookDetailActivity.BOOK_DATA,
            BookDataAdapter().getItemAsBookItem(data)
        )
        startActivity(
            intent
        )
    }
}