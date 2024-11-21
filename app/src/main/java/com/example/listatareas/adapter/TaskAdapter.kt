package com.example.listatareas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatareas.data.Task
import com.example.listatareas.databinding.ItemTaskBinding

class TaskAdapter(var items: List<Task>,
                  val onItemClick:(Int)-> Unit,
                  val onItemCheck:(Int)-> Unit,
                  val onItemDelete:(Int)-> Unit
                  ): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding= ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task= items[position]
        holder.render(task)
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        holder.binding.checkbox.setOnClickListener {
            onItemCheck(position)
        }
        holder.binding.deleteBTN.setOnClickListener{
            onItemDelete(position)
        }


    }

    fun updateItems (items: List<Task>)
    {

        this.items= items
        notifyDataSetChanged()
    }

}

class ViewHolder( val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root)
{
    fun render (task: Task)
    {
        binding.nameTextView.text= task.name
        binding.checkbox.isChecked=task.done

    }

}