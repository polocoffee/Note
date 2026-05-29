package com.banklannister.notes.core.data.repository

import com.banklannister.notes.core.data.mapper.toImages
import com.banklannister.notes.core.data.remote.api.ImagesApi
import com.banklannister.notes.core.domain.model.Images
import com.banklannister.notes.core.domain.repository.ImagesRepository
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
): ImagesRepository {


    override suspend fun searchImages(query: String): Images? {
        return imagesApi.searchImages(query)?.toImages()
    }
}