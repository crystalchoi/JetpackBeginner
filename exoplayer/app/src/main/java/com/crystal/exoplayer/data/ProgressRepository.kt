package com.crystal.exoplayer.data

import com.crystal.exoplayer.network.EpisodeApiService
import com.crystal.exoplayer.network.EpisodeData


interface ProgressRepository {
    suspend fun getInfos() : List<EpisodeData>
}

class DefaultProgressRepository(val episodeService: EpisodeApiService) : ProgressRepository {
    override suspend fun getInfos(): List<EpisodeData> {
        return episodeService.getInfos()
    }

}

class NetworkEpisodeRepository(
    private val episodeApiService: EpisodeApiService
) : ProgressRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getInfos(): List<EpisodeData> = episodeApiService.getInfos()
}