package com.banklannister.notes.note_list.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.banklannister.notes.core.di.AppModule
import com.banklannister.notes.core.presentation.MainActivity
import com.banklannister.notes.core.presentation.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
@UninstallModules(AppModule::class)
class NoteListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun insertNote() {
        insertNote(1)
    }

    @Test
    fun insertNote_NoteIsDisplay() {
        insertNote(1)
        assertNoteIsDisplay(1)
    }

    @Test
    fun insertNote_NoteIsNoteDisplay() {
        insertNote(1)
        assertNoteIsDisplay(1)

        deleteNote(1)
        assertNoteIsNotOnDisplay(1)
    }

    @Test
    fun noteListScreenEndToEndTest() {
        insertNote(1)
        assertNoteIsDisplay(1)

        insertNote(2)
        assertNoteIsDisplay(2)

        insertNote(3)
        assertNoteIsDisplay(3)

        deleteNote(1)
        assertNoteIsNotOnDisplay(1)
        assertNoteIsDisplay(2)
        assertNoteIsDisplay(3)

        insertNote(4)
        deleteNote(3)
        assertNoteIsNotOnDisplay(3)
        assertNoteIsDisplay(2)
        assertNoteIsDisplay(4)
    }

    private fun insertNote(noteNumber: Int) {
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_FAB)
            .performClick()

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("title $noteNumber")

        composeRule.onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
            .performTextInput("description $noteNumber")

        composeRule.onNodeWithTag(TestTags.SAVE_BUTTON)
            .performClick()
    }

    private fun deleteNote(noteNumber: Int) {
        composeRule.onNodeWithContentDescription(
            TestTags.DELETE_NOTE + "title $noteNumber"
        )
            .performClick()
    }

    private fun assertNoteIsDisplay(noteNumber: Int) {
        composeRule.onNodeWithText("title $noteNumber")
            .assertIsDisplayed()
    }


    private fun assertNoteIsNotOnDisplay(noteNumber: Int) {
        composeRule.onNodeWithText("title $noteNumber")
            .assertIsNotDisplayed()
    }


//    @Test
//    fun insertNote() {
//        composeRule.onNodeWithTag(TestTags.ADD_NOTE_FAB)
//            .performClick()
//
//        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
//            .performTextInput("title")
//
//        composeRule.onNodeWithTag(TestTags.DESCRIPTION_TEXT_FIELD)
//            .performTextInput("description")
//
//        composeRule.onNodeWithTag(TestTags.SAVE_BUTTON)
//            .performClick()
//
//        composeRule.onNodeWithText("title")
//            .assertIsDisplayed()
//
//        composeRule.onNodeWithContentDescription(
//            TestTags.DELETE_NOTE + "title"
//        )
//            .performClick()
//
//        composeRule.onNodeWithText("title")
//            .assertIsNotDisplayed()
//
//    }


}














