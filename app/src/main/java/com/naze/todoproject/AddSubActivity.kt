package com.naze.todoproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.naze.todoproject.adapter.SubAdapter
import com.naze.todoproject.data.SubData
import com.naze.todoproject.databinding.ActivityAddSubBinding

class AddSubActivity : AppCompatActivity() {

    lateinit var subAdapter: SubAdapter
    val datas = mutableListOf<SubData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subAdapter= SubAdapter(this)
        binding.subList.adapter = subAdapter
        binding.subList.layoutManager = LinearLayoutManager(this)

        val sub_edit: EditText = binding.subEditName
        val sub_color: ImageView = binding.colorPicker
        sub_color.setColorFilter(Color.parseColor("#000000"))
        var color:String = "#000000"

        binding.subAddBtn.setOnClickListener {

            if (sub_edit.text.isNotEmpty()) {
                                datas.apply {
                    add(SubData(color, sub_edit.text.toString()))
                    Log.d(
                        "SubList",
                        color + "/" + sub_edit.text.toString()
                    )

                }
                subAdapter.datas = datas
                subAdapter.notifyDataSetChanged()

                sub_edit.text = null

            } else {
                Toast.makeText(this,"과목 명이 빈 칸일 수 없습니다.",Toast.LENGTH_SHORT).show()
            }

        }

    }

}