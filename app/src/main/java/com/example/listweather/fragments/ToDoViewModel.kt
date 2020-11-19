package com.example.listweather.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    val readallToDo : LiveData<List<ToDo>>
    private val repository : ToDoRepository

    init {
        val toDoDao = ToDoDatabase.getDatabase(application).todoDao()
        repository = ToDoRepository(toDoDao)
        readallToDo = repository.readallToDo
    }

    fun addToDo(toDo: ToDo){
        viewModelScope.launch(Dispatchers.IO){
            repository.addToDo(toDo)
        }
    }

    fun deleteToDo(toDo: ToDo){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteToDo(toDo)
        }
    }
}