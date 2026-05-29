package com.banklannister.notes.note_list.domain.use_case

import com.banklannister.notes.core.domain.model.NoteItem
import com.banklannister.notes.core.domain.repository.NoteRepository

class DeleteNotes(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: NoteItem) {
        noteRepository.deleteNote(note)
    }
}