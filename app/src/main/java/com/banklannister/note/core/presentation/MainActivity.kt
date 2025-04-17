package com.banklannister.note.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.banklannister.note.add_note.presentation.AddNoteScreen
import com.banklannister.note.core.presentation.ui.theme.NoteTheme
import com.banklannister.note.note_list.presentation.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTheme {
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Screen.NoteList
        ) {

            composable<Screen.NoteList> {
                NoteListScreen(
                    onNavigateToAddNote = {
                        navController.navigate(Screen.AddNote)
                    }
                )
            }

            composable<Screen.AddNote> {
                AddNoteScreen(
                    onSave = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

