package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto
import retrofit2.Retrofit

interface MarsPhotosRepository {
    suspend fun getMarsPhotos() : List<MarsPhoto>
}

class DefaultMarsPhotosRepository(val retrofitService: MarsApiService) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return retrofitService.getPhotos()
    }


}