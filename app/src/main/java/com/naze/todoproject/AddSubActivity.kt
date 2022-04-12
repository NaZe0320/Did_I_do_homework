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
import com.naze.todoproject.dialog.CustomDelDialog
import com.naze.todoproject.dialog.CustomEditDialog
import com.naze.todoproject.dto.Sub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSubBinding

    private lateinit var subAdapter: SubAdapter
    private val subData = mutableListOf<Sub>()

    private lateinit var db:UserDatabase
    private var subColor:String = "#F6E58D"


    init {
        instance = this
        Log.v("init","init")
    }

    companion object {
        private var instance:AddSubActivity?=null

        fun getInstance():AddSubActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabase.getInstance(applicationContext)!!

        subAdapter= SubAdapter(this)
        subAdapter!!.subData = subData
        binding.subList.adapter = subAdapter
        binding.subList.layoutManager = LinearLayoutManager(this)
        //recyclerView 설정

        //deleteAll() 데이터베이스 초기화
        //List 초기화
        initialize()

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
            subAdapter.subData = subData

            dbInsert(subColor,subEdit.text.toString())
            Log.v("Color",binding.colorPicker.colorFilter.toString()+"+"+subColor)
            //갱신
            subAdapter.notifyDataSetChanged() //바꿔줘야 하지만 이해도 부족으로 우선 오류가 발생하지 않도록

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

    private fun initialize(){
        CoroutineScope(Dispatchers.IO).launch {
            val savedSub = db.subDao.getAllSub()
            if(savedSub.isNotEmpty()) {
                subData.addAll(savedSub)
                subAdapter.subData = subData

                subAdapter.notifyDataSetChanged()
            }
            Log.d("DB", subData.toString())
        }
    } //최초 초기화

    private fun deleteAll(){
        CoroutineScope(Dispatchers.IO).launch {
            db.subDao.deleteAllSub()
        }
    }

    fun deleteSub(sub: Sub) {
        val dialog = CustomDelDialog(this)
        dialog.showDialog()
        dialog.setOnClickListener(object : CustomDelDialog.OnDialogClickListener {
            override fun onClicked() {
                CoroutineScope(Dispatchers.IO).launch {
                    db.subDao.deleteSub(sub)
                } //삭제 딜레이 주기
                subData.remove(sub)
                subAdapter?.notifyDataSetChanged()
            }
        })
    }

    fun editSub(position: Int, sub: Sub) {
        val dialog = CustomEditDialog(this)
        dialog.showDialog()
        dialog.setOnClickListener(object :CustomEditDialog.OnDialogClickListener {
            override fun onClicked(color: String, subject: String) {
                TODO("Not yet implemented")
            }

        })
    } //추후 수정 예정


}