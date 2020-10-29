package com.example.listweather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.listweather.R

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val wView : View = inflater.inflate(R.layout.fragment_weather,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Weather"

        return wView
    }

}