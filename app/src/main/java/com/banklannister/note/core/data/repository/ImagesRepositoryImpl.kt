package com.banklannister.note.core.data.repository

import com.banklannister.note.core.data.mapper.toImages
import com.banklannister.note.core.data.remote.api.ImagesApi
import com.banklannister.note.core.domain.model.Images
import com.banklannister.note.core.domain.repository.ImagesRepository
import javax.inject.Inject


class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
) : ImagesRepository {

    override suspend fun searchImages(
        query: String
    ): Images? {
        return imagesApi.searchImages(query)?.toImages()
    }

}