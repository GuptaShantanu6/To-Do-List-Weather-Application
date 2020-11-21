package com.example.listweather.fragments

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SwipeToDelete(var adapter : ToDoAdapter) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

    private lateinit var mToDoViewModel: ToDoViewModel

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        var pos : Int = viewHolder.adapterPosition
//        adapter.deleteItem(pos)
//        var pos : Int = viewHolder.adapterPosition
//        var user = ToDo(1,"China")
//        adapter.deleteToDo(user)
//        var pos : Int = viewHolder.adapterPosition
//        adapter.deleteToDo(pos)

    }
}