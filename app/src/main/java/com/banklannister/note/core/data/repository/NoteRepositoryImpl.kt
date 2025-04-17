package com.banklannister.note.core.data.repository

import com.banklannister.note.core.data.local.NoteDb
import com.banklannister.note.core.data.mapper.toNoteEntityForDelete
import com.banklannister.note.core.data.mapper.toNoteEntityForInsert
import com.banklannister.note.core.data.mapper.toNoteItem
import com.banklannister.note.core.domain.model.NoteItem
import com.banklannister.note.core.domain.repository.NoteRepository


class NoteRepositoryImpl(
    noteDb: NoteDb
) : NoteRepository {

    private val noteDao = noteDb.noteDao

    override suspend fun upsertNote(noteItem: NoteItem) {
        noteDao.upsertNoteEntity(noteItem.toNoteEntityForInsert())
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        noteDao.deleteNoteEntity(noteItem.toNoteEntityForDelete())
    }

    override suspend fun getAllNotes(): List<NoteItem> {
        return noteDao.getAllNoteEntities().map { it.toNoteItem() }
    }
}