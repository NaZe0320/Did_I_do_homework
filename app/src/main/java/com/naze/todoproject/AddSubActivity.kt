package com.naze.todoproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerBottomSheet
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.naze.todoproject.adapter.SubAdapter
import com.naze.todoproject.data.SubData
import com.naze.todoproject.databinding.ActivityAddSubBinding

class AddSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubBinding

    private lateinit var subAdapter: SubAdapter
    private val subData = mutableListOf<SubData>()

    private var subColor:String = "#f6e58d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subAdapter= SubAdapter(this)
        binding.subList.adapter = subAdapter
        binding.subList.layoutManager = LinearLayoutManager(this)

        binding.colorPicker.setColorFilter(Color.parseColor(subColor))

        binding.subAddBtn.setOnClickListener {
            onClickAddBtn()
        }
        binding.colorPicker.setOnClickListener {
            onClickColorBtn()
        }

    }

    private fun onClickAddBtn() {
        val subEdit: EditText = binding.subEditName

        if (subEdit.text.isNotEmpty()) {
            subData.apply {
                add(SubData(subColor, subEdit.text.toString()))
                Log.d(
                    "SubList",
                    subColor + "/" + subEdit.text.toString()
                )
            }
            subAdapter.datas = subData
            subAdapter.notifyDataSetChanged()

            subEdit.text = null
        } else {
            Toast.makeText(this,"과목 명이 빈 칸일 수 없습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickColorBtn() {
        MaterialColorPickerDialog
            .Builder(this)
            .setTitle("색상 선택")
            .setColorShape(ColorShape.CIRCLE)
            .setColors(resources.getStringArray(R.array.themeColorHex))
            .setColorListener { _, colorHex ->
                binding.colorPicker.setColorFilter(Color.parseColor(colorHex))
                subColor = colorHex
            }.show()
    }

}