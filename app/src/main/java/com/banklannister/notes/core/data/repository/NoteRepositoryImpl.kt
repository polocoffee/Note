package com.banklannister.notes.core.data.repository

import com.banklannister.notes.core.data.local.NoteDatabase
import com.banklannister.notes.core.data.mapper.toNoteEntityForDelete
import com.banklannister.notes.core.data.mapper.toNoteEntityForInsert
import com.banklannister.notes.core.data.mapper.toNoteItem
import com.banklannister.notes.core.domain.model.NoteItem
import com.banklannister.notes.core.domain.repository.NoteRepository

class NoteRepositoryImpl(
    noteDb: NoteDatabase
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