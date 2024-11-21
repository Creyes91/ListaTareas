package com.example.listatareas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import com.example.listatareas.R
import com.example.listatareas.data.Task
import com.example.listatareas.data.providers.TaskDAO
import com.example.listatareas.databinding.ActivityTaskBinding


class TaskActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_task_id ="ID"
    }

    lateinit var task: Task
    lateinit var taskDAO: TaskDAO

    lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO= TaskDAO(this)


        val id=intent.getLongExtra(EXTRA_task_id, -1L)

        if (id != -1L) {
            task = taskDAO.findById(id)!!
            binding.nameTextField.editText?.setText(task.name)
        }else {
            var task = Task(-1, "")
        }

        val editText= binding.nameTextField?.editText


            editText?.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("Not yet implemented")
                }

                override fun afterTextChanged(s: Editable?) {


                }

            }

        binding.saveButton.setOnClickListener{
            var name = binding.nameTextField.editText?.text.toString()
            if(name.isEmpty()){
                binding.nameTextField.error= "Escribe algo"
            return@setOnClickListener}
            if(name.length> 50){
                binding.nameTextField.error="Texto demasiado largo"
            return@setOnClickListener}

            task.name=name
            if (task.id==-1L)
            taskDAO.insert(task)
            else taskDAO.update(task)

            finish()

        }





    }
}