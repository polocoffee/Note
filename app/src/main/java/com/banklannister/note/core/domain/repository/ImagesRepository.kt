package com.banklannister.note.core.domain.repository

import com.banklannister.note.core.domain.model.Images

interface ImagesRepository {

    suspend fun searchImages(query: String): Images?

}