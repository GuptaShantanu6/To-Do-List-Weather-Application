package com.example.listweather.fragments

import androidx.lifecycle.LiveData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val readallToDo : LiveData<MutableList<ToDo>> = toDoDao.readallToDo()

    suspend fun addToDo(todo : ToDo){
        toDoDao.addToDo(todo)
    }

}