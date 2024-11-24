package com.example.listatareas.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatareas.R
import com.example.listatareas.adapter.TaskAdapter
import com.example.listatareas.adapter.dateAdapter
import com.example.listatareas.data.Days
import com.example.listatareas.data.Task
import com.example.listatareas.data.providers.TaskDAO
import com.example.listatareas.databinding.ActivityMainBinding
import com.example.listatareas.utils.DBManager
import java.time.LocalDate
import java.util.Calendar
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var taskDao: TaskDAO
    var taskList: MutableList<Task> = mutableListOf()
    lateinit var adapter: TaskAdapter
    lateinit var dateAdapter: dateAdapter
    var dateList: MutableList<Days> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDao= TaskDAO(this)

        dateAdapter= dateAdapter(dateList)

        initRvDates()



       adapter=TaskAdapter(taskList,{

           val task = taskList[it]
          showTask(task)
       },{
        val task= taskList[it]
           checkTask(task)
        },{
            val task= taskList[it]
           deleteTask(task)
       }
       )
        binding.recyclerView.adapter= adapter
        binding.dateRecyclerView.adapter= dateAdapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.dateRecyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.addFAButton.setOnClickListener{
            intent= Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }



    }

    private fun initRvDates() {

        var calendar= Calendar.getInstance()
        var mesActual= calendar.get(Calendar.MONTH)
        var añoActual=calendar.get(Calendar.YEAR)
        var fecha: Days

        calendar.set(Calendar.MONTH, mesActual)
        calendar.set(Calendar.DAY_OF_MONTH,1)
        calendar.set(Calendar.YEAR, añoActual)

        val diasMes= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            fecha= Days(0,"");

        for (dia in 1..diasMes){
            fecha.day=dia

            dateList.add(fecha)

        }





    }

    private fun showTask(task: Task) {

        intent= Intent(this, TaskActivity::class.java)
        intent.putExtra(TaskActivity.EXTRA_task_id, task.id)
        startActivity(intent)

    }

    private fun checkTask(task: Task) {
        task.done= !task.done
        taskDao.update(task)
        taskDao.update(task)

    }

    private fun deleteTask(task: Task) {

        AlertDialog.Builder(this)
            .setTitle("Borrar?")
            .setMessage("Estas seguro que quieres borrar esta tarea?")
            .setPositiveButton(android.R.string.ok) { dialog, wich->
                taskDao.delete(task)
                taskList.remove(task)
                adapter.updateItems(taskList)
            }
            .setNegativeButton(android.R.string.cancel,null)
            .setIcon(R.drawable.ic_delete)
            .show()
    }


    override fun onResume() {
        super.onResume()
        taskList= taskDao.findAll().toMutableList()
        adapter.updateItems(taskList)
    }



}