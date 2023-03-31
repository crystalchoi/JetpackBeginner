package com.crystal.photogallarycompose

import android.util.Log
import com.crystal.photogallarycompose.api.FlickrApi
import com.crystal.photogallarycompose.data.GalleryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val TAG =  "PhotoGalleryRepository"

class PhotoRepository {
    private val flickrApi : FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://www.flickr.com/")
            .baseUrl("https://api.flickr.com/")
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        flickrApi = retrofit.create()
    }

    suspend fun fetchContents() = flickrApi.fetchContents()
    suspend fun fetchPhotos(): List<GalleryItem> = flickrApi.fetchPhotos().photos.galleryItems
}