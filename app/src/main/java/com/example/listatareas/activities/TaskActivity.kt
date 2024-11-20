package com.example.listatareas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.listatareas.R
import com.example.listatareas.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}