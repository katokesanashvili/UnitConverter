package com.example.unitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import login
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mainActivity, login()).commit()


    }
}