package com.naze.todoproject

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.naze.todoproject.adapter.SubAdapter
import com.naze.todoproject.adapter.ToDoAdapter
import com.naze.todoproject.database.UserDatabase
import com.naze.todoproject.databinding.ActivityMainBinding
import com.naze.todoproject.dto.Sub
import com.naze.todoproject.dto.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    
    private lateinit var todoAdapter: ToDoAdapter
    private val todoData = mutableListOf<ToDo>()

    private lateinit var subAdapter: SubAdapter
    private val subData = mutableListOf<Sub>()
    
    private lateinit var db:UserDatabase
    
    init {
        instance = this
    }
    
    companion object {
        private var instance:MainActivity?=null

        fun getInstance():MainActivity? {
            return instance
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabase.getInstance(applicationContext)!!

        todoAdapter = ToDoAdapter(this)
        todoAdapter!!.todoData = todoData

        binding.todoList.adapter = todoAdapter
        binding.todoList.layoutManager = LinearLayoutManager(this)

        initialize()

        binding.settingButton.setOnClickListener {
            val intent = Intent(this, AddSubActivity::class.java)
            startActivity(intent)
        }

        binding.floatingActionButton.setOnClickListener {
            onClickAddBtn()
        }

    }

    private fun initialize() {
        CoroutineScope(Dispatchers.IO).launch {
            val savedTodo = db.todoDao.getAllTodo()
            if(savedTodo.isNotEmpty()) {
                todoData.addAll(savedTodo)
                todoAdapter.todoData = todoData

                todoAdapter.notifyDataSetChanged()
            }
            Log.d("DB",savedTodo.toString())
        }
    }

    private fun onClickAddBtn() {
        todoData.apply {
            add(ToDo("핵공학개론","핵공학개론 과제","2022-04-12","2022-04-13",true))
        }
        todoAdapter.todoData = todoData
        dbInsert("핵공학개론","핵공학개론 과제","2022-04-12","2022-04-13",true)

        todoAdapter.notifyDataSetChanged()
    }

    private fun dbInsert(subName: String, todoName: String, startD: String, endD: String, checked: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao.insertTodo(ToDo(subName,todoName,startD,endD,checked))
        }
    }

}