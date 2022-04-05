package com.naze.todoproject.viewHolder

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.R
import com.naze.todoproject.data.SubData

class SubViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val sub_color: ImageView = itemView.findViewById(R.id.color_selected_item)
    private val sub_name: TextView = itemView.findViewById(R.id.sub_name_item)

    fun bind(item: SubData){
        sub_color.setColorFilter(Color.parseColor(item.sub_color))
        sub_name.text = item.sub_name.toString()
    }
}
