package com.banklannister.notes.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.banklannister.notes.add_note.presentation.AddNoteScreen
import com.banklannister.notes.core.presentation.ui.theme.NotesTheme
import com.banklannister.notes.note_list.presentation.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Navigation()

            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.SplashScreen
    ) {

        composable<Screen.SplashScreen> {
            SplashScreenAnimation(
                onNavigateToNoteList = {
                    navController.navigate(Screen.NoteList) {
                        popUpTo(Screen.SplashScreen){
                            inclusive =true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }



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
