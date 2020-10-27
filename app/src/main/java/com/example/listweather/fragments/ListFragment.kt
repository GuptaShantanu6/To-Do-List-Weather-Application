package com.example.listweather.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
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
            if (title.isNotEmpty()){
                val todo = ToDo(title)
                todoList.add(todo)
                adapter.notifyItemInserted(todoList.size - 1)
                Toast.makeText(activity,"Task Successfully Added",Toast.LENGTH_LONG).show()
                hideKeyboard()
                inputTask.text.clear()

            }
            else{
                Toast.makeText(activity,"Please Enter Something",Toast.LENGTH_SHORT).show()
            }
        }

        return x
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}