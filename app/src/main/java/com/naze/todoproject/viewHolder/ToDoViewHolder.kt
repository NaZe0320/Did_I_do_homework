package com.naze.todoproject.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.MainActivity
import com.naze.todoproject.databinding.TodoItemBinding
import com.naze.todoproject.dto.ToDo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ToDoViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val mainActivity = MainActivity.getInstance()
    var sTodo: ToDo?= null
    var sPosition: Int? = null

    fun bind(item: ToDo, position: Int){
        this.sTodo = item
        this.sPosition = position
        //?

        binding.todoCheckedItem.isChecked = item.checked
        binding.todoSubItem.text = item.subject.toString()
        binding.todoNameItem.text = item.todo.toString()
        binding.todoDateItem.text = item.sDate.toString() + " ~ " +item.eDate.toString()
        val startDate = LocalDate.parse(item.sDate.toString(), DateTimeFormatter.ISO_DATE)
        val endDate = LocalDate.parse(item.eDate.toString(), DateTimeFormatter.ISO_DATE)
        binding.todoDdayItem.text = "D-day"
    }

    init {
        //내부 반응
    }
}