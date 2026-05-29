package com.banklannister.notes.add_note.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.banklannister.notes.core.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpsertNoteTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var upsertNote: UpsertNote

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        upsertNote = UpsertNote(fakeNoteRepository)
    }


    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "", description =  "desc", imageUrl = "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty desc, return false`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "title", description =  "", imageUrl = "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert invalid note, return true`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "title", description =  "desc", imageUrl = "image"
        )

        assertThat(isInserted).isTrue()
    }


}