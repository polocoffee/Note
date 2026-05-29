package com.banklannister.notes.note_list.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.banklannister.notes.core.data.repository.FakeNoteRepository
import com.banklannister.notes.core.domain.model.NoteItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DeleteNotesTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var deleteNote: DeleteNotes

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNotes(fakeNoteRepository)

        fakeNoteRepository.shouldHaveFilledList(true)


    }


    @Test
    fun `delete note from list`() = runTest {
        val note = NoteItem(
            "c title 2",
            "desc 2",
            "url2",
            2
        )
        deleteNote.invoke(note)

        assertThat(
            fakeNoteRepository.getAllNotes().contains(note)
        ).isFalse()
    }

}