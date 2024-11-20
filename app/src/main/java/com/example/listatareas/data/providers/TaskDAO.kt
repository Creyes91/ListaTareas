package com.example.listatareas.data.providers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.listatareas.data.Task
import com.example.listatareas.utils.DBManager

class TaskDAO(var context: Context) {

    lateinit var  db: SQLiteDatabase

    private fun open()
    {
        db= DBManager(context).writableDatabase

    }

    private fun  close()
    {
        db.close()

    }

    fun insert(task: Task)
    {
        open()

        val values = ContentValues().apply{
            put(Task.COLUMN_NAME, task.name)
            put(Task.COLUMN_DONE, task.done)
        }

        try {
           val id = db.insert(Task.TABLE_NAME, null, values)
        }catch (e: Exception){
            Log.e("DB", e.stackTraceToString())
        }
        finally {
            close()
        }

    }

    fun update(task: Task)
    {
        open()

        val values = ContentValues().apply {
            put(Task.COLUMN_NAME, task.name)
            put(Task.COLUMN_DONE, task.done)
        }

        try {
            val updatedRows= db.update(Task.TABLE_NAME, values, "${Task.COLUMN_ID}=${task.id}",null)
        } catch (e: Exception)
        {
            Log.e("DB", e.stackTraceToString())
        } finally {
            close()
        }

    }

    fun delete (task: Task)
    {
        open()

        val values= ContentValues().apply {
            put(Task.COLUMN_NAME, task.name)
            put(Task.COLUMN_ID, task.id)
        }

        try{
            val deletedRows= db.delete(Task.TABLE_NAME,"1", null)
        }catch (e:Exception)
        {
            Log.e("DB", e.stackTraceToString())
        }finally {
            close()
        }


    }


    fun findById(id: Long): Task?
    {
        open()
        val projection = arrayOf(Task.COLUMN_ID,Task.COLUMN_NAME, Task.COLUMN_DONE)

        try {
            val cursor = db.query(
                Task.TABLE_NAME,
                projection,
                "${Task.COLUMN_ID}=$id",
                null,
                null,
                null,
                null
            )
            if (cursor.moveToNext())
            {
                val id= cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_ID))
                val done= cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_DONE))!= 0
                val name= cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME))

                return Task(id, name, done)
            }

        }catch (e: Exception){Log.e("DB", e.stackTraceToString())}
        finally {
            close()
        }
        return null

    }

    fun findAll(): List<Task>
    {
        open()
        var list: MutableList<Task> = mutableListOf()
        val projection= arrayOf(Task.COLUMN_ID, Task.COLUMN_NAME, Task.COLUMN_DONE)

        try {
            val cursor = db.query(
                Task.TABLE_NAME,                    // The table to query
                projection,                         // The array of columns to return (pass null to get all)
                null,                       // The columns for the WHERE clause
                null,                   // The values for the WHERE clause
                null,                       // don't group the rows
                null,                         // don't filter by row groups
                null   )                      // The sort order

            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(Task.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(Task.COLUMN_NAME))
                val done = cursor.getInt(cursor.getColumnIndexOrThrow(Task.COLUMN_DONE)) != 0

                val task= Task(id,name,done)
                list.add(task)
            }
        }catch (e:Exception)
        {
            Log.e("DB", e.stackTraceToString())
        }
        return list
    }

}