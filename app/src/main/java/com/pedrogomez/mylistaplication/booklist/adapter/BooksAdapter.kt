package com.pedrogomez.mylistaplication.booklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedrogomez.mylistaplication.booklist.models.bookresponse.Item

class BooksAdapter(
    private val items: List<Item>,
    private val onClickItemListener: BookViewHolder.OnClickItemListener
) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val inflater = LayoutInflater.from(
            parent.context
        )
        return BookViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        holder.setData(
            items[position],
            onClickItemListener
        )
    }

    override fun getItemCount() = items.size
}