package com.naze.todoproject.viewHolder

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.R
import com.naze.todoproject.dto.Sub

class SubViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val subColor: ImageView = itemView.findViewById(R.id.color_selected_item)
    private val subName: TextView = itemView.findViewById(R.id.sub_name_item)
    private val subBtnModify: Button = itemView.findViewById(R.id.btn_modify_item)
    private val subBtnDelete: Button = itemView.findViewById(R.id.btn_delete_item)

    fun bind(item: Sub){
        subColor.setColorFilter(Color.parseColor(item.color))
        subName.text = item.subject.toString()

        subBtnDelete.setOnClickListener {

        }
    }
}
