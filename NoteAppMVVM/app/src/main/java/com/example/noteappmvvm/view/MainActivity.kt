package com.example.noteappmvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappmvvm.databinding.ActivityMainBinding
import com.example.noteappmvvm.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        adapter = NoteAdapter { note ->
            viewModel.deleteNote(note)
            Toast.makeText(this, "Deleted: ${note.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter

        viewModel.notes.observe(this) { notes ->
            adapter.submitList(notes)
        }

        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isBlank() || description.isBlank()) {
                Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addNote(title, description)

            binding.etTitle.text.clear()
            binding.etDescription.text.clear()
        }
    }
}
