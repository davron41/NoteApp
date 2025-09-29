package com.example.noteappmvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteappmvvm.model.Note

class NoteViewModel : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>(emptyList())
    val notes: LiveData<List<Note>> = _notes

    private var noteIdCounter = 0

    fun addNote(title: String, description: String) {
        val newNote = Note(
            id = noteIdCounter++,
            title = title,
            description = description
        )
        val updatedList = listOf(newNote) + (_notes.value ?: emptyList())
        _notes.value = updatedList
    }

    fun deleteNote(note: Note) {
        val currentList = _notes.value ?: emptyList()
        _notes.value = currentList.filter { it.id != note.id }
    }
}