package com.example.listweather.fragments

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToDo(todo: ToDo)

    @Query("SELECT * FROM todo_table")
    fun readallToDo() : LiveData<MutableList<ToDo>>
}