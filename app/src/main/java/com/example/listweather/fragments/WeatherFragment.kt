package com.example.listweather.fragments

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import com.beust.klaxon.Klaxon
import com.example.listweather.R
import com.example.listweather.fragments.api.WeatherJson
import kotlinx.android.synthetic.main.fragment_weather.*
import okhttp3.*
import java.io.IOException
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val wView : View = inflater.inflate(R.layout.fragment_weather,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "                           Quick Weather            "

        val enterButton : Button = wView.findViewById<Button>(R.id.enterButton)
        var city : String = "Chicago"
        val API : String = "6cd35acf8099b62877b7bff4e1933765"
        val progress_bar : ProgressBar = wView.findViewById<ProgressBar>(R.id.progress_bar)

//        var URL : String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API"

        enterButton.setOnClickListener {
//            inputCity.setText("")
            hideKeyboard()
            progress_bar.visibility = View.VISIBLE
            city = inputCity.text.toString()
            var URL : String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API"
            val client = OkHttpClient()
            val request = Request.Builder().url(URL).build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful){
                        System.err.println("Response Not Succesful")
                        Toast.makeText(activity,"Wrong Input",Toast.LENGTH_SHORT).show()
                    }
                    val json = response.body!!.string()
                    val myData = Klaxon().parse<WeatherJson>(json)
//                    println("Data=$myData")
                    if (myData != null) {
                        println(myData.main.temp)
                        var temp : Int = myData.main.temp.toInt()
                        var temp1 : Double = temp.toDouble()
                        temp1-=273.15
                        temp1 = Math.round(temp1*1000.0) / 1000.00
                        temp1 = Math.round(temp1*100.0) / 100.0
                        var temp2 : String = temp1.toString()
//                        temp_text.text = temp2
                        temp2+="°C"
                        println(temp2)
                        var h : String = myData.main.humidity.toString()
                        h+=" %"
                        activity?.runOnUiThread(Runnable {
                            progress_bar.visibility = View.GONE
                            temp_text.text = temp2
                            humidity_text.text = h
//                            sunrise_text.text = myData.sys.sunrise.toString()
//                            sunset_text.text = myData.sys.sunset.toString()
                            var x : String = myData.wind.speed.toString()
                            x+=" m/Sec"
                            wind_text.text = x
                            var srise = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(
                                myData.sys.sunrise.toLong()
                            ))
                            srise = srise.toString()
                            srise=srise.substring(11,16)
                            if (srise.substring(0,2)>="12"){
                                srise+=" Pm"
                            }
                            else srise+=" Am"
                            sunrise_text.text = srise
                            var sset = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(
                                myData.sys.sunset.toLong()
                            ))
                            sset = sset.toString()
                            sset = sset.substring(11,16) + " Pm"
                            sunset_text.text = sset

                        })

                    }
                    println(Thread.currentThread())
                }

            })
            client.dispatcher.executorService.shutdown()

        }

        return wView
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

