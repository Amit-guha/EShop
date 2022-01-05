package com.example.e_marketapplication.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.e_marketapplication.R

class SuccessfulScreen : AppCompatActivity() {
    private lateinit var btnSuccess:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_screen)

        Statusebar()
        SetupToolbar()
        btnSuccess=findViewById(R.id.Successsubmit)
        btnSuccess.setBackgroundColor(resources.getColor(R.color.black2))
        btnSuccess.setOnClickListener {
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun SetupToolbar() {
        //Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setTitle("Successful screen")
        setSupportActionBar(toolbar)

    }

    //For Satus bar color
    private fun Statusebar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.black, this.theme)

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.black)
        }
    }
}