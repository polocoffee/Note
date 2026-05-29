package com.banklannister.notes.core.domain.model

import androidx.annotation.StyleRes

data class NoteItem(
    var title: String,
    var description: String,
    var imageUrl: String,
    val dateAdded: Long,
    val id: Int = 0
)
