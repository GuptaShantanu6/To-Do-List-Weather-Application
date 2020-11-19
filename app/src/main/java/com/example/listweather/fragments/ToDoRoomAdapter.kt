package com.example.listweather.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listweather.R
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoRoomAdapter : RecyclerView.Adapter<ToDoRoomAdapter.MyViewHolder>() {

    private var todoList = emptyList<ToDo>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentToDo = todoList[position]
        holder.itemView.tvTitle.text = currentToDo.title
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todo : List<ToDo>){
        this.todoList = todo
        notifyDataSetChanged()

    }


}