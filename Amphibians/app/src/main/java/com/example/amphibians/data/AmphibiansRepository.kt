package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.example.amphibians.network.AnimalData


interface AmphibiansRepository {
    suspend fun getInfos() : List<AnimalData>
}

class DefaultAmphibiansRepository(val retrofitService: AmphibiansApiService) : AmphibiansRepository {
    override suspend fun getInfos(): List<AnimalData> {
        return retrofitService.getInfos()
    }

}

class NetworkMarsPhotosRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getInfos(): List<AnimalData> = amphibiansApiService.getInfos()
}