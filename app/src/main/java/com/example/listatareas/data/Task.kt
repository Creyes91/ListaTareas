package com.example.listatareas.data

class Task(val ID : Long, var name: String, var done: Boolean )
{

   companion object
   {
       const val TABLE_NAME = "Task"
       const val COLUMN_ID = "Task"
       const val COLUMN_NAME = "Task"
       const val COLUMN_DONE = "Task"


    }


}