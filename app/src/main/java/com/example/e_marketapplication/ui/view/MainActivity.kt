package com.example.e_marketapplication.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.e_marketapplication.R
import com.example.e_marketapplication.data.repository.MainRepository
import com.example.emartket.ui.viewmodel.MainViewModel
import com.example.emartket.ui.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    //First Api call related textviw Ratingbar
    private lateinit var tvname: TextView
    private lateinit var tvopening: TextView
    private lateinit var tvClosing: TextView
    private lateinit var ratingBar: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Chage the color of Statusbar Cololr
        Statusebar()

        //Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))



        SetupUI()
        SetuPViewModel()

    }

    private fun SetuPViewModel() {
        val repository = MainRepository()
        val viewmodelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewmodelFactory).get(MainViewModel::class.java)
    }



    private fun SetupUI() {
        //find the id---->Storeinfo
        tvname = findViewById(R.id.tv_name)
        tvopening = findViewById(R.id.tv_opening)
        tvClosing = findViewById(R.id.tv_closing)
        ratingBar = findViewById(R.id.ratingbar)
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