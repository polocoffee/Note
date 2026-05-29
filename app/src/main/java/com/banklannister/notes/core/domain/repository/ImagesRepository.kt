package com.banklannister.notes.core.domain.repository

import com.banklannister.notes.core.domain.model.Images

interface ImagesRepository {

    suspend fun searchImages(query: String): Images?
}