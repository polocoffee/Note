package com.banklannister.notes.add_note.presentation

data class AddNoteState(
    val imageUrl: String = "",
    val title: String = "",
    val description : String = "",

    val isImageDialogShowing: Boolean = false,
    val imageList: List<String> = emptyList(),
    val searchImageQuery: String = "",
    val isLoadingImage: Boolean = false
)
