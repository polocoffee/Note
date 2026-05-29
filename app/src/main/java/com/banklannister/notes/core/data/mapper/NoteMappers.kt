package com.banklannister.notes.core.data.mapper

import com.banklannister.notes.core.data.local.NoteEntity
import com.banklannister.notes.core.domain.model.NoteItem


fun NoteItem.toNoteEntityForInsert(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}


fun NoteItem.toNoteEntityForDelete(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}

fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id ?: 0
    )
}