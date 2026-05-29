package com.banklannister.notes.core.di

import android.app.Application
import androidx.room.Room
import com.banklannister.notes.add_note.domain.use_case.SearchImages
import com.banklannister.notes.add_note.domain.use_case.UpsertNote
import com.banklannister.notes.core.data.local.NoteDatabase
import com.banklannister.notes.core.data.remote.api.ImagesApi
import com.banklannister.notes.core.data.remote.api.ImagesApi.Companion.BASE_URL
import com.banklannister.notes.core.data.repository.ImagesRepositoryImpl
import com.banklannister.notes.core.data.repository.NoteRepositoryImpl
import com.banklannister.notes.core.domain.repository.ImagesRepository
import com.banklannister.notes.core.domain.repository.NoteRepository
import com.banklannister.notes.note_list.domain.use_case.DeleteNotes
import com.banklannister.notes.note_list.domain.use_case.GetAllNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        noteDb: NoteDatabase
    ): NoteRepository {
        return NoteRepositoryImpl(noteDb)
    }

    @Provides
    @Singleton
    fun provideGetAllNoteUseCase(
        noteRepository: NoteRepository
    ): GetAllNotes {
        return GetAllNotes(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(
        noteRepository: NoteRepository
    ): DeleteNotes {
        return DeleteNotes(noteRepository)
    }

    @Provides
    @Singleton
    fun provideUpsertNoteUseCase(
        noteRepository: NoteRepository
    ): UpsertNote {
        return UpsertNote(noteRepository)
    }

    @Provides
    @Singleton
    fun provideImageApi(): ImagesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ImagesApi::class.java)
    }



    @Provides
    @Singleton
    fun provideImagesRepository(
        imagesApi: ImagesApi
    ): ImagesRepository {
        return ImagesRepositoryImpl(imagesApi)
    }

    @Provides
    @Singleton
    fun provideSearchImageUseCase(
        imagesRepository: ImagesRepository
    ): SearchImages {
        return SearchImages(imagesRepository)
    }


}