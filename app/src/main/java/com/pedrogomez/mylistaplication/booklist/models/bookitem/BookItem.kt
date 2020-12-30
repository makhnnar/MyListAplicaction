package com.pedrogomez.mylistaplication.booklist.models.bookitem

import android.os.Parcel
import android.os.Parcelable
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.AccessInfo
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.SaleInfo
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.SearchInfo
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.VolumeInfo
import java.io.Serializable

class BookItem(
    val accessInfo: AccessInfo?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val searchInfo: SearchInfo? = null,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
):Serializable{

}