package com.banklannister.notes.note_list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.banklannister.notes.MainCoroutineRule
import com.banklannister.notes.core.data.repository.FakeNoteRepository
import com.banklannister.notes.core.domain.model.NoteItem
import com.banklannister.notes.note_list.domain.use_case.DeleteNotes
import com.banklannister.notes.note_list.domain.use_case.GetAllNotes
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getAllNote: GetAllNotes
    private lateinit var deleteNote: DeleteNotes

    private lateinit var noteListViewModel: NoteListViewModel

    @Before
    fun setup() {

        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNotes(fakeNoteRepository)
        getAllNote = GetAllNotes(fakeNoteRepository)

        noteListViewModel = NoteListViewModel(getAllNote, deleteNote)

    }

    @Test
    fun `get note from an empty list`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(false)
        noteListViewModel.loadNote()

        assertThat(
            noteListViewModel.noteListState.value.isEmpty()
        ).isTrue()
    }


    @Test
    fun `get note from a filled list, note is not empty`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)
        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(
            noteListViewModel.noteListState.value.isNotEmpty()
        ).isTrue()
    }

    @Test
    fun `delete note from a list`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val note = noteListViewModel.noteListState.value[0]

        noteListViewModel.deleteNote(note)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(
            noteListViewModel.noteListState.value.contains(note)
        ).isFalse()
    }

    @Test
    fun `sort note by date`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNote()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        var notes = noteListViewModel.noteListState.value
      for(i in 0..notes.size -2 ){
          assertThat(notes[i].dateAdded).isLessThan(notes[i+1].dateAdded)
      }

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        notes = noteListViewModel.noteListState.value
        for(i in 0..notes.size -2 ){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        notes = noteListViewModel.noteListState.value
        for(i in 0..notes.size -2 ){
            assertThat(notes[i].dateAdded).isLessThan(notes[i+1].dateAdded)
        }
    }


    @Test
    fun `sort note by title`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val notes = noteListViewModel.noteListState.value
        for(i in 0..notes.size -2 ){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }

    }


}