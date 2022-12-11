package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>   // viewModel 에 있던 거..
}

class DefaultMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
//        return  MarsApi.retrofitService.getPhotos()
        return marsApiService.getPhotos()
    }
}


class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}