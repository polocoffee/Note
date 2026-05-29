package com.banklannister.notes.core.data.mapper

import com.banklannister.notes.core.data.remote.dto.ImageListDto
import com.banklannister.notes.core.domain.model.Images


fun ImageListDto.toImages(): Images {
    return Images(
        images = hits?.map { imageDto ->
            imageDto.previewURL ?: ""
        } ?: emptyList()
    )
}
