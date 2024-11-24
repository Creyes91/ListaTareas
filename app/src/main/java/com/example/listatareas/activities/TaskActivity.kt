package com.example.listatareas.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.TimePicker
import com.example.listatareas.R
import com.example.listatareas.data.Task
import com.example.listatareas.data.providers.TaskDAO
import com.example.listatareas.databinding.ActivityTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Locale
import java.util.Timer


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
            task = Task(-1, "")
        }








        //activar o desactivar botton guardar

        val editText= binding.nameTextField.editText!!

        editText.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.saveButton.isEnabled= s?.isNotEmpty()==true
            }

            override fun afterTextChanged(s: Editable?) {

            }


        })




        //Click boton guardar
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

        binding.datePickerActions.editText?.setOnClickListener{
            datePicker()
        }
        binding.timePickerActions.editText?.setOnClickListener{
            timePicker()
        }





    }
    private fun datePicker()
    {
        val initialDate = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(Calendar.YEAR, year)
                date.set(Calendar.MONTH, month)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = simpleDateFormat.format(date.time)
                binding.datePickerActions.editText?.setText(formattedDate)
            }, initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH)
        )
// Show the dialog
        datePickerDialog.show()
    }

    private fun timePicker()
    {
        val hora= Calendar.getInstance()

        val timeDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute->
            val time =  Calendar.getInstance()
            time.set(Calendar.HOUR, hourOfDay)
            time.set(Calendar.MINUTE, minute)

            val simpleTimeFormat= SimpleDateFormat("hh:mm a", Locale.getDefault())
            val formattedTime= simpleTimeFormat.format(time.time)
            binding.timePickerActions.editText?.setText(formattedTime)

        },hora.get(Calendar.HOUR), hora.get(Calendar.MINUTE),true )

        timeDialog.show()

    }



}