package com.example.listatareas.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listatareas.R
import com.example.listatareas.utils.DBManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var db = DBManager(this).writableDatabase

        val values= ContentValues()

    }
}