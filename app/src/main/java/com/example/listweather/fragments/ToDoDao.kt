package com.example.listweather.fragments

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToDo(todo: ToDo)

    @Query("SELECT * FROM todo_table")
    fun readallToDo() : LiveData<List<ToDo>>

    @Delete
    suspend fun deleteToDo(todo: ToDo)
}