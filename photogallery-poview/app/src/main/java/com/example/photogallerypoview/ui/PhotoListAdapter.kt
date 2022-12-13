package com.example.photogallerypoview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.photogallerypoview.R
import com.example.photogallerypoview.api.GalleryItem
import com.example.photogallerypoview.databinding.ListLayoutGalleryBinding

class PhotoViewHolder(private val binding: ListLayoutGalleryBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(galleryItem: GalleryItem) {
        binding.itemImageView.load(galleryItem.url) {
            placeholder(R.drawable.bill_up_close)
        }
    }
}

class PhotoListAdapter(private val galleryItems: List<GalleryItem>)
    : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListLayoutGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return galleryItems.size
    }
}