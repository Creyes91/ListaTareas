package com.example.listatareas.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object
    {
        const val DATABASE_VERSION =1
        const val DATABASE_NAME= "Tareas.db"

        private const val  SQL_CREATE_TABLE =
            "CREATE TABLE TASK (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DONE INTEGER)"

        private const val SQL_DELETE_TABLE= "DROP TABLE IF EXISTS TASK"

    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_TABLE)
        db.execSQL(SQL_CREATE_TABLE)
    }
}
