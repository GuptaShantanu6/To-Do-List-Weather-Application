package com.example.listweather.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listweather.R
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoRoomAdapter(val listener : IToDoRoomAdapter) : RecyclerView.Adapter<ToDoRoomAdapter.MyViewHolder>() {

    private var todoList = emptyList<ToDo>()
//    private lateinit var mToDoViewModel: ToDoViewModel

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textview = itemView.findViewById<TextView>(R.id.tvTitle)
        val delete_button = itemView.findViewById<ImageView>(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false))
        viewHolder.delete_button.setOnClickListener {
            listener.onItemClicked(todoList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentToDo = todoList[position]
        holder.itemView.tvTitle.text = currentToDo.title
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todo : List<ToDo>) {
        this.todoList = todo
        notifyDataSetChanged()
    }

}

interface IToDoRoomAdapter {
    fun onItemClicked(todo : ToDo)
}