package com.example.noteappmvvm.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappmvvm.R
import com.example.noteappmvvm.model.Note

class NoteAdapter(private val onDeleteClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, onDeleteClick)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(itemView: View, private val onDeleteClick: (Note) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val titleText: TextView = itemView.findViewById(R.id.textTitle)
        private val descriptionText: TextView = itemView.findViewById(R.id.textDescription)
        private val deleteButton: TextView = itemView.findViewById(R.id.textDelete)

        fun bind(note: Note) {
            titleText.text = note.title
            descriptionText.text = note.description

            deleteButton.setOnClickListener {
                onDeleteClick(note)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }
}