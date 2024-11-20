package com.example.listatareas.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatareas.R
import com.example.listatareas.adapter.TaskAdapter
import com.example.listatareas.data.Task
import com.example.listatareas.data.providers.TaskDAO
import com.example.listatareas.databinding.ActivityMainBinding
import com.example.listatareas.utils.DBManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var taskDao: TaskDAO
    var taskList: List<Task> = emptyList()
    lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDao= TaskDAO(this)



       adapter=TaskAdapter(taskList){

           val task = taskList[it]
           task.done= !task.done
           taskDao.update(task)
           adapter.updateItems(taskList)
       }

        binding.recyclerView.adapter= adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)






    }

    override fun onResume() {
        super.onResume()
        taskList=taskDao.findAll()
        adapter.updateItems(taskList)
    }

}