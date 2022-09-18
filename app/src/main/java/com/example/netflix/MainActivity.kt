package com.example.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var add: Button
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        add = findViewById(R.id.add)
        text = findViewById(R.id.text)
        val notesAdapter = NotesAdapter()
        recyclerView.adapter = notesAdapter
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val notesDao = NotesDatabase.getDatabase(applicationContext).notesDao()
        val notesRepository = NotesRepository(notesDao)
        val mainViewModel = ViewModelProvider(this, MainViewModelFactory(notesRepository)).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(this) {
            notesAdapter.submitList(it)
            Log.d("list", "onCreate: $it")
        }
        add.setOnClickListener{
            if(text.text!!.isEmpty()){
                text.error = "Empty"
            }else{
                mainViewModel.insertNotes(Notes(0,text.text.toString()))
                Toast.makeText(this,"DONE!",Toast.LENGTH_SHORT).show()
                text.text = null
            }
        }
    }

}