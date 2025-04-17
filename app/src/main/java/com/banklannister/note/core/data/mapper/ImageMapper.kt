package com.banklannister.note.core.data.mapper

import com.banklannister.note.core.data.remote.dto.ImageListDto
import com.banklannister.note.core.domain.model.Images


fun ImageListDto.toImages(): Images {
    return Images(
        images = hits?.map { imageDto ->
            imageDto.previewURL ?: ""
        } ?: emptyList()
    )
}