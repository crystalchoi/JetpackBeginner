package com.example.photogallerypoview

import android.util.Log
import com.example.photogallerypoview.api.FlickrApi
import com.example.photogallerypoview.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

private const val TAG = "PhotoRepository"
class PhotoRepository {

    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://www.flickr.com/")
            .baseUrl("https://api.flickr.com/")
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        flickrApi = retrofit.create()
//        flickrApi = retrofit.create<FlickrApi>()
    }

    suspend fun fetchPhotos() : List<GalleryItem> {
        Log.d(TAG, " galleryItems.size: $flickrApi.fetchPhotos().photos.galleryItems.size")
        return flickrApi.fetchPhotos().photos.galleryItems
    }

}