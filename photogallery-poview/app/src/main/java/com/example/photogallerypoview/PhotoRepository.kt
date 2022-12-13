package com.example.photogallerypoview

import android.util.Log
import com.example.photogallerypoview.api.FlickrApi
import com.example.photogallerypoview.api.GalleryItem
import com.example.photogallerypoview.api.PhotoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.Query

private const val TAG = "PhotoRepository"
class PhotoRepository {
//class PhotoRepository(private val flickrApi: FlickrApi) {

    private val flickrApi: FlickrApi

    init {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(PhotoInterceptor()).build()

        val retrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("https://www.flickr.com/")
            .baseUrl("https://api.flickr.com/")
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        flickrApi = retrofit.create()   // for DI
//        flickrApi = retrofit.create<FlickrApi>()
    }

    suspend fun fetchPhotos() : List<GalleryItem> {
        val result = flickrApi.fetchPhotos().photos.galleryItems
        Log.d(TAG, "fetchPhotos: $result.size")
        return result
    }

    suspend fun searchPhotos(query: String) : List<GalleryItem> {
        val result = flickrApi.searchPhotos(query).photos.galleryItems
        Log.d(TAG, " searchPhotos: $query $result.size")
        return result
    }
}