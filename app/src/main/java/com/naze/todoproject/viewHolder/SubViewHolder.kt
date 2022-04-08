package com.naze.todoproject.viewHolder

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterButton
import androidx.recyclerview.widget.RecyclerView
import com.naze.todoproject.AddSubActivity
import com.naze.todoproject.R
import com.naze.todoproject.database.UserDatabase.Companion.getInstance
import com.naze.todoproject.databinding.SubItemBinding
import com.naze.todoproject.dto.Sub
import kotlinx.coroutines.NonDisposableHandle.parent

class SubViewHolder(private val binding: SubItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val addSubActivity = AddSubActivity.getInstance()
    var sSub: Sub? = null
    var sPosition: Int? = null

    private val subColor: ImageView = binding.colorSelectedItem
    private val subName: TextView = binding.subNameItem
    private val subBtnModify: ImageFilterButton = binding.btnModifyItem
    private val subBtnDelete: ImageFilterButton = binding.btnDeleteItem


    fun bind(item: Sub, position: Int){
        this.sSub = item
        this.sPosition = position
        subColor.setColorFilter(Color.parseColor(item.color))
        subName.text = item.subject.toString()
    }

    init {
        subBtnDelete.setOnClickListener {
            Log.v("delete_sub",sSub.toString())
            Log.v("delete_sub",sPosition.toString())
            addSubActivity?.deleteSub(sSub!!)
        }

        subBtnModify.setOnClickListener {

        }
    }
}
