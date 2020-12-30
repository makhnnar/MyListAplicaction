package com.pedrogomez.mylistaplication.booklist.repository

import androidx.lifecycle.MutableLiveData
import com.pedrogomez.mylistaplication.extensions.isValid
import com.pedrogomez.mylistaplication.booklist.model.BooksListResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.pedrogomez.mylistaplication.booklist.model.Result

class BooksRepository( private val client : HttpClient) {

    //@KtorExperimentalAPI
    suspend fun getListOfBooks(
        liveData : MutableLiveData<Result<BooksListResponse>>,
        query : String,
        page:Int
    ){
        liveData.postValue(
            Result.Loading(true)
        )

        try{
            val requestUrl = "https://www.googleapis.com/books/v1/volumes?q=$query&startIndex=${page*10}&maxResults=10"

            val response =
                client.request<BooksListResponse>(requestUrl) {
                    method = HttpMethod.Get
                }
            liveData.postValue(Result.Success(response))
        }catch (e : java.lang.Exception){
            if (e.message.isValid()) {
                liveData.postValue(Result.Error.RecoverableError(Exception(e.message)))
            }else{
                liveData.postValue(Result.Error.NonRecoverableError(Exception("Un-traceable")))
            }
        }

    }

}