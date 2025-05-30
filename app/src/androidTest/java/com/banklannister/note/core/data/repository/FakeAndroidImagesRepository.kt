package com.banklannister.note.core.data.repository

import com.banklannister.note.core.domain.model.Images
import com.banklannister.note.core.domain.repository.ImagesRepository


class FakeAndroidImagesRepository : ImagesRepository {

    private var shouldReturnError = false
    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun searchImages(
        query: String
    ): Images? {
        return if (shouldReturnError) {
            null
        } else {
            Images(
                listOf("image1", "image2", "image3", "image4", "image5", "image6")
            )
        }
    }
}