package com.example.listatareas.data

class Task(var id : Long, var name: String, var done: Boolean= false)
{

   companion object
   {
       const val TABLE_NAME = "TASK"
       const val COLUMN_ID = "ID"
       const val COLUMN_NAME = "NAME"
       const val COLUMN_DONE = "DONE"

    }


}