package com.example.listweather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listweather.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val x : View = inflater.inflate(R.layout.fragment_list,container,false)
//        return inflater.inflate(R.layout.fragment_list, container, false)

        var todoList = mutableListOf(
            ToDo("Hey Everyone"),
            ToDo("my"),
            ToDo("name"),
            ToDo("is"),
            ToDo("shantanu"),
            ToDo("gupta"),
            ToDo("Hey !"),
            ToDo("1"),
            ToDo("2"),
            ToDo("@")
        )

        val recycleViewId = x.findViewById<View>(R.id.recycleViewId) as RecyclerView

        val adapter = ToDoAdapter(todoList)
        recycleViewId.adapter = adapter
        recycleViewId.layoutManager = LinearLayoutManager(this.context)

        val addButton : Button = x.findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            val title = inputTask.text.toString()
            val todo = ToDo(title)
            todoList.add(todo)
            adapter.notifyItemInserted(todoList.size-1)
        }

        return x
    }

}