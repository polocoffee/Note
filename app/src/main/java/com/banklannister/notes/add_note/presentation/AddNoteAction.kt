package com.banklannister.notes.add_note.presentation

sealed interface AddNoteAction {

    data class UpdateTitle(val newTitle: String) : AddNoteAction
    data class UpdateDescription(val newDescription: String) : AddNoteAction
    data class UpdateSearchImageQuery(val newQuery: String) : AddNoteAction
    data class PickImage(val imageUrl: String) : AddNoteAction

    data object UpdateImageDialog : AddNoteAction
    data object SaveNote : AddNoteAction
}