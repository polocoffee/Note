package com.banklannister.notes.add_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.notes.add_note.domain.use_case.SearchImages
import com.banklannister.notes.add_note.domain.use_case.UpsertNote
import com.banklannister.notes.add_note.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val upsertNote: UpsertNote,
    private val searchImages: SearchImages,
) : ViewModel() {


    private val _addNoteState = MutableStateFlow(AddNoteState())
    val addNoteState = _addNoteState.asStateFlow()


    private val _noteSaveChannel = Channel<Boolean>()
    val noteSaveChannel = _noteSaveChannel.receiveAsFlow()

    fun onAction(action: AddNoteAction) {
        when (action) {
            is AddNoteAction.UpdateTitle -> {
                _addNoteState.update {
                    it.copy(title = action.newTitle)
                }
            }

            is AddNoteAction.UpdateDescription -> {
                _addNoteState.update {
                    it.copy(description = action.newDescription)
                }
            }


            is AddNoteAction.PickImage -> {
                _addNoteState.update {
                    it.copy(imageUrl = action.imageUrl)
                }
            }


            is AddNoteAction.UpdateSearchImageQuery -> {
                _addNoteState.update {
                    it.copy(searchImageQuery = action.newQuery)
                }

                searchImage(action.newQuery)
            }

            AddNoteAction.UpdateImageDialog -> {
                _addNoteState.update {
                    it.copy(isImageDialogShowing = !it.isImageDialogShowing)
                }
            }


            AddNoteAction.SaveNote -> {
                viewModelScope.launch {
                    val isSaved = upsertNote(
                        title = addNoteState.value.title,
                        description = addNoteState.value.description,
                        imageUrl = addNoteState.value.imageUrl
                    )

                    _noteSaveChannel.send(isSaved)
                }
            }

        }
    }


    suspend fun upsertNote(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {
        return upsertNote.invoke(
            title = title,
            description = description,
            imageUrl = imageUrl
        )

    }


    private var searchJob: Job? = null

    fun searchImage(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            searchImages.invoke(query)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _addNoteState.update {
                                it.copy(imageList = emptyList())
                            }
                        }

                        is Resource.Loading -> {
                            _addNoteState.update {
                                it.copy(isLoadingImage = result.isLoading)
                            }
                        }

                        is Resource.Success -> {
                            _addNoteState.update {
                                it.copy(imageList = result.data?.images ?: emptyList())
                            }
                        }
                    }
                }
        }
    }
}
