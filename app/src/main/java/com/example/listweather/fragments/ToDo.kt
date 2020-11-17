package com.example.listweather.fragments

import androidx.room.Entity

@Entity(tableName = "todo_table")
data class ToDo (
    val title : String
)
