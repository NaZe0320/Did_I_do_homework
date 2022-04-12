package com.naze.todoproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.databinding.TodoItemBinding
import com.naze.todoproject.dto.ToDo
import com.naze.todoproject.viewHolder.ToDoViewHolder

class ToDoAdapter(private val context: Context):RecyclerView.Adapter<ToDoViewHolder>() {
    var todoData = mutableListOf<ToDo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(todoData[position],position)
    }

    override fun getItemCount(): Int {
        return this.todoData.size
    }
}