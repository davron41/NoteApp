package com.note_application.feature_note.presentation.notes

import com.note_application.feature_note.domain.util.NoteOrder
import com.note_application.feature_note.domain.model.Note

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object  RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}