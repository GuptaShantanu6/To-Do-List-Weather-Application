package com.example.listweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.listweather.fragments.InfoFragment
import com.example.listweather.fragments.ListFragment
import com.example.listweather.fragments.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listfragment = ListFragment()
        val weatherFragment = WeatherFragment()
        val infofragment = InfoFragment()

        makeCurrentFragment(listfragment)

        bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.listid -> makeCurrentFragment(listfragment)
                R.id.weatherid -> makeCurrentFragment(weatherFragment)
//                R.id.infoid -> makeCurrentFragment(infofragment)
            }
            true
        }


    }

    private fun makeCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
    }
}