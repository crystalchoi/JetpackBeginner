package com.example.marsphotos.fake

import com.example.marsphotos.data.DefaultMarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto
import com.example.marsphotos.fake.FakeMarsApiService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest

import org.junit.Test

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )

        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())

    }

    @Test
    fun defaultMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = DefaultMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )

        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())

    }
}