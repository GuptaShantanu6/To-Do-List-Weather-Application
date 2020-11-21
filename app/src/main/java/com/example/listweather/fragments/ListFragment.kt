package com.example.listweather.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listweather.R
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), IToDoRoomAdapter {

    private lateinit var mToDoViewModel: ToDoViewModel
    private lateinit var mToDoViewModel_adapter : ToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val x : View = inflater.inflate(R.layout.fragment_list,container,false)
//        return inflater.inflate(R.layout.fragment_list, container, false)

        mToDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

//        var todoList = mutableListOf(
//            ToDo("Hey Everyone"),
//            ToDo("my"),
//            ToDo("name"),
//            ToDo("is"),
//            ToDo("shantanu"),
//            ToDo("gupta"),
//            ToDo("Hey !"),
//            ToDo("1"),
//            ToDo("2"),
//            ToDo("@")
//        )

        val recycleViewId = x.findViewById<View>(R.id.recycleViewId) as RecyclerView
        recycleViewId.itemAnimator = SlideInLeftAnimator()
        val adapter = ToDoRoomAdapter(this)
        recycleViewId.adapter = adapter
        recycleViewId.layoutManager = LinearLayoutManager(this.context)
//        val adapter = ToDoAdapter(todoList)
//        recycleViewId.adapter = adapter
//        recycleViewId.layoutManager = LinearLayoutManager(this.context)

//        mToDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        mToDoViewModel.readallToDo.observe(viewLifecycleOwner, Observer {todo ->
            adapter.setData(todo)
        })

        val addButton : Button = x.findViewById<Button>(R.id.addButton)

//        val delete_button : Button = x.findViewById<Button>(R.id.delete_button)

        addButton.setOnClickListener {
//            val title = inputTask.text.toString()
//
//            if (title.isNotEmpty()){
//                var xy : Int = if (adapter.itemCount==0){
//                    0
//                } else {
//                    1
//                }
//                val todo = ToDo(title)
//                todoList.add(xy,todo)
//                adapter.notifyItemInserted(xy)
//                Toast.makeText(activity,"Task Successfully Added",Toast.LENGTH_LONG).show()
//                hideKeyboard()
//                inputTask.text.clear()
//
//            }
//            else{
//                Toast.makeText(activity,"Please Enter Something",Toast.LENGTH_SHORT).show()
//            }
            insertDataToDatabase()
        }

//        var itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter))
//        itemTouchHelper.attachToRecyclerView(recycleViewId)



        (activity as AppCompatActivity).supportActionBar?.title = "                         To-Do List            "

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

    private fun insertDataToDatabase(){
        val task = inputTask.text.toString()
        if (!task.isBlank()){
            //create User Object
            val user = ToDo(0,task)

            //add data to database
            mToDoViewModel.addToDo(user)
            hideKeyboard()
            inputTask.text.clear()
            Toast.makeText(activity,"Task has been added",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(activity,"Please Enter Something and Try Again",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onItemClicked(todo: ToDo) {
        mToDoViewModel.deleteToDo(todo)
        Toast.makeText(activity,"${todo.title} is Deleted",Toast.LENGTH_SHORT).show()
    }

}