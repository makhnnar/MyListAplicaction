package com.pedrogomez.mylistaplication.booklist.di

import com.pedrogomez.mylistaplication.booklist.repository.BooksRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { okHttpKtor }
    single { BooksRepository(get()) }
}

private val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json)
    }
}