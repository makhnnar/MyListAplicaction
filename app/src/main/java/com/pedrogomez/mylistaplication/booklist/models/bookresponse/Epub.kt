package com.pedrogomez.mylistaplication.booklist.models.bookresponse

import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    val acsTokenLink: String? = null,
    val isAvailable: Boolean?
)