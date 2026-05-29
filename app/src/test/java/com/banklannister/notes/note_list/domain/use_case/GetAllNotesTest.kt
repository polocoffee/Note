package com.banklannister.notes.note_list.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.banklannister.notes.core.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetAllNotesTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getAllNotes: GetAllNotes

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        getAllNotes = GetAllNotes(fakeNoteRepository)


    }


    @Test
    fun `get note from empty list`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(false)
        val note = getAllNotes.invoke(true)

        assertThat(note.isEmpty())
    }

    @Test
    fun `get note sort by title`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)
        val note = getAllNotes.invoke(true)

        for (i in 0..note.size - 2) {

            assertThat(note[i].title).isLessThan(note[i + 1].title)
        }
    }

    @Test
    fun `get note sort by date added`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)
        val note = getAllNotes.invoke(false)

        for (i in 0..note.size - 2) {

            assertThat(note[i].dateAdded).isLessThan(note[i + 1].dateAdded)
        }
    }

}