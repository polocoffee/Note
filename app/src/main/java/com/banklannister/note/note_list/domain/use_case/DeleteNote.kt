package com.banklannister.note.note_list.domain.use_case

import com.banklannister.note.core.domain.model.NoteItem
import com.banklannister.note.core.domain.repository.NoteRepository


class DeleteNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: NoteItem) {
        noteRepository.deleteNote(note)
    }

}