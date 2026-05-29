package com.banklannister.notes.add_note.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.banklannister.notes.MainCoroutineRule
import com.banklannister.notes.add_note.domain.use_case.SearchImages
import com.banklannister.notes.add_note.domain.use_case.UpsertNote
import com.banklannister.notes.core.data.repository.FakeImagesRepository
import com.banklannister.notes.core.data.repository.FakeNoteRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddNoteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var addNoteViewModel: AddNoteViewModel
    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var fakeImagesRepository: FakeImagesRepository
    private lateinit var upsertNote: UpsertNote

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        fakeImagesRepository = FakeImagesRepository()
        val upsertNote = UpsertNote(fakeNoteRepository)
        val searchImages = SearchImages(fakeImagesRepository)
        addNoteViewModel = AddNoteViewModel(upsertNote, searchImages)
    }

    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "", description = "desc", imageUrl = "image"
        )

        Truth.assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title", description = "", imageUrl = "image"
        )

        Truth.assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert invalid note, return true`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title", description = "desc", imageUrl = "image"
        )

        Truth.assertThat(isInserted).isTrue()
    }

    @Test
    fun `search image with empty query`() = runTest {
        addNoteViewModel.searchImage("")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }


    @Test
    fun `search image with valid query and return error, because image list is empty`() = runTest {
        fakeImagesRepository.setShouldReturnError(true)
        addNoteViewModel.searchImage("query")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(addNoteViewModel.addNoteState.value.imageList.isEmpty()).isTrue()
    }


    @Test
    fun `search image with valid query and return true`() = runTest {
        addNoteViewModel.searchImage("query")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(addNoteViewModel.addNoteState.value.imageList.isNotEmpty()).isTrue()
    }


}