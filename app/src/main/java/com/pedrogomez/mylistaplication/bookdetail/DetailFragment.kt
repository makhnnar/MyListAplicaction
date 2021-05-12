package com.pedrogomez.mylistaplication.bookdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pedrogomez.mylistaplication.booklist.models.bookitem.BookItem
import com.pedrogomez.mylistaplication.databinding.FragmentDetailBinding
import com.pedrogomez.mylistaplication.utils.extensions.remove
import org.koin.android.viewmodel.ext.android.getViewModel

class DetailFragment : Fragment() {

    private lateinit var  binding : FragmentDetailBinding

    private lateinit var bookData : BookItem

    companion object{

        fun newInstance(bookData : BookItem):DetailFragment{
            val fragment = DetailFragment()
            fragment.bookData = bookData
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
          savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        Glide.with(this)
            .load(
                bookData.cover
            ).into(
                binding?.ivBook!!
            )
        binding.tvTitle.text = bookData.title
        binding.tvAuthor.text = bookData.authors
        binding.tvDatePublication.text = bookData.datePublication
        binding.tvDescription.text = bookData.description
        binding.btnShowMore.setOnClickListener {
            openOnBrowser(
                bookData.prevLink?:""
            )
        }
        binding.btnBuyThis.setOnClickListener {
            openOnBrowser(
                bookData.buyLink?:""
            )
        }
        if(bookData.prevLink==null){
            binding.btnShowMore.remove()
        }
        if(bookData.buyLink==null){
            binding.btnBuyThis.remove()
        }

        return binding.root
    }

    private fun openOnBrowser(url:String){
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        startActivity(browserIntent)
    }

}