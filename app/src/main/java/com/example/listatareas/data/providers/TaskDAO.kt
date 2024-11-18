package com.example.listatareas.data.providers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.listatareas.data.Task
import com.example.listatareas.utils.DBManager

class TaskDAO(context: Context) {

    lateinit var  db: SQLiteDatabase

    fun open()
    {
        db= DBManager(context).writableDatabase

    }

    fun  close()
    {
        db.close()

    }

    fun insert(task: Task)
    {


    }

    fun update(task: Task)
    {


    }

    fun delete (task: Task)
    {


    }
}