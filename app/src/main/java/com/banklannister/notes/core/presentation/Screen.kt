package com.banklannister.notes.core.presentation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object SplashScreen : Screen


    @Serializable
    data object NoteList : Screen

    @Serializable
    data object AddNote : Screen
}