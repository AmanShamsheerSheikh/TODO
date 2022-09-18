package com.example.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter : ListAdapter<Notes, NotesAdapter.NotesViewHolder>(DiffUtil()) {


    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Notes>( ){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }
    }
    class NotesViewHolder(view : View): RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.item)
        fun bind(notes: Notes){
            textView.text = notes.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemlayout,parent,false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}