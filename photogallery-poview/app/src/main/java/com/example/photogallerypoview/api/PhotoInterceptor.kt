package com.example.photogallerypoview.api

import com.example.photogallerypoview.api.API_KEY
import okhttp3.HttpUrl

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


const val API_KEY = "97be5666ff4dcfe499ed19a7a5e06c5e"

class PhotoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val newUrl: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("extras", "url_s")
            .addQueryParameter("safe_search", "1")
            .build()

        val newRequest: Request = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}