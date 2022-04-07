package com.naze.todoproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.naze.todoproject.adapter.SubAdapter
import com.naze.todoproject.database.UserDatabase
import com.naze.todoproject.databinding.ActivityAddSubBinding
import com.naze.todoproject.dto.Sub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubBinding

    private lateinit var subAdapter: SubAdapter
    private val subData = mutableListOf<Sub>()

    private lateinit var db:UserDatabase
    private var subColor:String = "#F6E58D"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabase.getInstance(applicationContext)!!

        subAdapter= SubAdapter(this)
        binding.subList.adapter = subAdapter
        binding.subList.layoutManager = LinearLayoutManager(this)
        //recyclerView 설정

        //deleteAll() 데이터베이스 초기화
        //List 초기화
        refreshList()

        binding.colorPicker.setColorFilter(Color.parseColor(subColor)) //

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
                add(Sub(subEdit.text.toString(),subColor))
            }
            subAdapter.datas = subData
            subAdapter.notifyDataSetChanged() //갱신

            dbInsert(subColor,subEdit.text.toString())

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

    private fun dbInsert(subColor: String, subName: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.subDao.insertSub(Sub(subName,subColor))
        }
    }

    private fun refreshList(){
        CoroutineScope(Dispatchers.IO).launch {
            val savedSub = db.subDao.getAllSub()
            Log.d("DB", savedSub.toString())
                if(savedSub.isNotEmpty()) {
                    subData.addAll(savedSub)
                    Log.d("DB", subData.toString())
                    subAdapter.datas = subData
                    subAdapter.notifyDataSetChanged()
                }
        }
    }

    private fun deleteAll(){
        CoroutineScope(Dispatchers.IO).launch {
            db.subDao.deleteAllSub()
        }
    }



}