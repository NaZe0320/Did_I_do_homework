package com.naze.todoproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.R
import com.naze.todoproject.dto.Sub
import com.naze.todoproject.viewHolder.SubViewHolder

class SubAdapter(private val context: Context): RecyclerView.Adapter<SubViewHolder>() {

    var datas = mutableListOf<Sub>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sub_item,parent,false)
        return SubViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return this.datas.size
    }


}