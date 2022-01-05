package com.example.e_marketapplication.ui.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_marketapplication.R
import com.example.e_marketapplication.data.repository.MainRepository
import com.example.e_marketapplication.ui.adapter.ProductAdapter2
import com.example.emartket.data.model.PostProductsItem
import com.example.emartket.ui.viewmodel.MainViewModel
import com.example.emartket.ui.viewmodel.MainViewModelFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    //First Api call related textviw Ratingbar
    private lateinit var tvname: TextView
    private lateinit var tvopening: TextView
    private lateinit var tvClosing: TextView
    private lateinit var ratingBar: RatingBar

    //Second Api call
    private lateinit var productAdapter2: ProductAdapter2
    private lateinit var recyclerView: RecyclerView

    val list = arrayListOf<PostProductsItem>()
    val listquantity = ArrayList<String>()


    @RequiresApi(Build.VERSION_CODES.O)
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
        SetupFirstAPICall()
        SetupRecylerview()
        SetupSecondAPICall()

    }

    private fun SetupRecylerview() {
        recyclerView = findViewById(R.id.recycler_item)
        productAdapter2 = ProductAdapter2()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = productAdapter2
    }

    private fun SetupSecondAPICall() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun SetupFirstAPICall() {
        viewModel.getstrore()
        viewModel.myResponse.observe(this, Observer { response ->
            // Log.d("Mainactivity", response.body()?.name.toString())

            if (response.isSuccessful) {
                tvname.text = response.body()?.name
                ratingBar.stepSize = 0.01f
                ratingBar.rating = response.body()?.rating?.toFloat()!!

                val open: String = response.body()?.openingTime.toString()
                val Opentime = open

                val close: String = response.body()?.closingTime.toString()
                val clostime = close

                //Opening Time
                //15:01:01.772Z --> Remove last 5 digit (https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/storeInfo)
                if (Opentime.contains("Z")) {
                    val result = Opentime.dropLast(5)
                    val result2 =
                        LocalTime.parse(result).format(DateTimeFormatter.ofPattern("h:mma"))
                    tvopening.setText(result2.toString())

                    //https://virtserver.swaggerhub.com/m-tul/opn-mobile-challenge-api/1.0.0/storeinfo
                } else {
                    val result =
                        LocalTime.parse(Opentime).format(DateTimeFormatter.ofPattern("h:mma"))
                    tvopening.text = result
                }


                //Closing Time
                //15:01:01.772Z --> Remove last 5 digit (https://c8d92d0a-6233-4ef7-a229-5a91deb91ea1.mock.pstmn.io/storeInfo)
                if (clostime.contains("Z")) {
                    val result = clostime.dropLast(5)
                    val result2 =
                        LocalTime.parse(result).format(DateTimeFormatter.ofPattern("h:mma"))
                    tvClosing.setText(result2.toString())

                    //https://virtserver.swaggerhub.com/m-tul/opn-mobile-challenge-api/1.0.0/storeinfo
                } else {
                    val result =
                        LocalTime.parse(clostime).format(DateTimeFormatter.ofPattern("h:mma"))
                    tvClosing.text = result
                }


                /*   val open: String= response.body()?.openingTime.toString()
                   val time = open


                   val result = LocalTime.parse(time).format(DateTimeFormatter.ofPattern("h:mma"))
                   tvopening.text=result

                   val result1 = LocalTime.parse(response.body()?.closingTime.toString()).format(DateTimeFormatter.ofPattern("h:mma"))
                   tvClosing.text=result1*/

                /*  val localTime: LocalTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalTime.now()
                } else {
                    TODO("VERSION.SDK_INT < O")
                }
                val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
                println(localTime.format(dateTimeFormatter))
                */
            }
        })

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