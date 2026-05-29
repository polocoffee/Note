package com.banklannister.notes.core.data.repository

import com.banklannister.notes.core.domain.model.Images
import com.banklannister.notes.core.domain.model.NoteItem
import com.banklannister.notes.core.domain.repository.ImagesRepository
import com.banklannister.notes.core.domain.repository.NoteRepository

class FakeAndroidImagesRepository : ImagesRepository {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun searchImages(query: String): Images? {
        return if (shouldReturnError){
            null
        } else {
            Images(listOf(
                "image1",
                "image2",
                "image3",
                "image4",
                "image5"
            ))
        }
    }


}

