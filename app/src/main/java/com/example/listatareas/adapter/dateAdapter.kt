package com.example.listatareas.adapter

import android.hardware.biometrics.BiometricManager.Strings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatareas.data.Days
import com.example.listatareas.databinding.ItemDateBinding

class dateAdapter(var days: List<Days>):RecyclerView.Adapter<dateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item= days[position]
       holder.render(item)

    }

    class ViewHolder(val binding: ItemDateBinding): RecyclerView.ViewHolder(binding.root)
    {

        fun render (day: Days)
        {
            /*binding.dayText.text=day.day.toString()
            binding.dayBtn.text=day.dayOfWeek*/
            binding.dayText.text=day.dayOfWeek.toString()
            binding.dayBtn.text= day.day.toString()
        }


    }


}



