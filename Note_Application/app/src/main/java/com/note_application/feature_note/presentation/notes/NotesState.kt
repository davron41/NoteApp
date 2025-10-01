package com.note_application.feature_note.presentation.notes

import com.note_application.feature_note.domain.util.NoteOrder
import com.note_application.feature_note.domain.util.OrderType
import com.note_application.feature_note.domain.model.Note


data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false
)
