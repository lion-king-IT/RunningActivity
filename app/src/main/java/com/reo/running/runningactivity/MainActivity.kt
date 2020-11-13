package com.reo.running.runningactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reo.running.runningactivity.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
                R.id.container_main_fragment,
                MainFragment()
        ).commit()
    }
}