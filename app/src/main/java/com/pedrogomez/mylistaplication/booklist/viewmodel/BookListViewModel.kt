package com.pedrogomez.mylistaplication.booklist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pedrogomez.mylistaplication.booklist.model.BooksListResponse
import com.pedrogomez.mylistaplication.booklist.repository.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import com.pedrogomez.mylistaplication.booklist.model.Result

class BookListViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    val scope : CoroutineScope = CoroutineScope(
        Dispatchers.IO
    )

    private val booksListLiveData = MutableLiveData<Result<BooksListResponse>>()

    fun observeData() : MutableLiveData<Result<BooksListResponse>> {
        return booksListLiveData
    }

    fun getReposFromGitHub(
        query : String,
        page:Int
    ){
        scope.launch {
            booksRepository.getListOfBooks(
                booksListLiveData,
                query,
                page
            )
        }
    }

    private fun getStringAsQueryString(query:String):String{
        val separatedQuery = query.trim().split(
            Regex("([\\s])+")
        )
        var finalQuery = ""
        if(separatedQuery.size>1){
            separatedQuery.forEach {
                finalQuery += "$it+"
            }
            return finalQuery.dropLast(1)
        }
        if(separatedQuery.size==1){
            return separatedQuery[0]
        }
        return finalQuery
    }

    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }

}