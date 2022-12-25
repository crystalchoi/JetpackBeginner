package com.example.marsphotos.fake

import org.junit.Test
import com.example.marsphotos.data.DefaultMarsPhotosRepository
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest


class NetworkMarsPhotosRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}

