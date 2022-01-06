package com.example.e_marketapplication.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_marketapplication.R
import com.example.e_marketapplication.data.repository.MainRepository
import com.example.e_marketapplication.ui.adapter.ItemtSelectedAdapter
import com.example.emartket.data.model.PostItem
import com.example.emartket.data.model.PostProductsItem
import com.example.emartket.ui.viewmodel.MainViewModel
import com.example.emartket.ui.viewmodel.MainViewModelFactory
import com.google.android.material.textfield.TextInputLayout

class ProductShow : AppCompatActivity() {

    private lateinit var productSelected: ItemtSelectedAdapter
    private lateinit var recyitem: RecyclerView
    private lateinit var TvTaka: TextView
    private lateinit var etDeliveryAd: TextInputLayout
    private lateinit var btnSubmit: Button
    private lateinit var progressbar: ProgressBar
    private lateinit var responseTv: TextView
    var selectedProduct = ArrayList<PostProductsItem>()
    var listquantity = ArrayList<String>()

    //viewmodel
    private lateinit var viewModelSecond: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_show)

        //Statusbar Cololr
        Statusebar()
        SetupToolbar()
        SetupIntent()
        setupUI()
        SetupRecyclerView()
        SetUpViewModel()
        setprice()
        btnclicEvent()


    }

    private fun SetupToolbar() {
        //Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.setTitle("Order summary screen")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow)

    }

    private fun SetupIntent() {
        //Data from MainActivity
        selectedProduct = intent?.getParcelableArrayListExtra<PostProductsItem>("extra")
            ?: throw IllegalStateException("array list is null")

        listquantity = intent?.getSerializableExtra("totalquantity") as ArrayList<String>
    }

    private fun btnclicEvent() {
        //Take data from textinputlayout
        val inputText = etDeliveryAd.editText?.text.toString()
        val postItem = PostItem(selectedProduct, inputText)


        btnSubmit.setOnClickListener {

            progressbar.setVisibility(View.VISIBLE);
            try {
                viewModelSecond.pushPost(postItem)
                viewModelSecond.myResponse3.observe(this, Observer { response ->
                    if (response.isSuccessful) {

                        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                        progressbar.setVisibility(View.INVISIBLE)

                        val responseString = "Response Code : ${response.code()}"
                        "Name : ${response.body()?.delivery_address}"

                        //set text if the request is successfull
                        responseTv.setText(responseString)

                        val intent = Intent(this,SuccessfulScreen::class.java)
                        startActivity(intent)



                    } else if (response.code() == 405) {
                        Toast.makeText(this, "This page isn’t working", Toast.LENGTH_SHORT).show()
                    } else {
                        //set text if the request is Failed
                        responseTv.setText("Error found is : ${response.code()}")
                    }
                })

            } catch (e: Exception) {
                //If any exception happened
                Toast.makeText(this, "Exception : ${e.message}", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun setprice() {
        var totalprice: Int = 0
        for (i in 0 until selectedProduct.size) {
            totalprice += selectedProduct.get(i).price.toInt()
            println("Total $totalprice")
        }

        TvTaka.setText(totalprice.toString())
    }

    private fun SetUpViewModel() {
        //Viewmodel and Respostiory initialization
        val repository = MainRepository()
        val viewmodelFactory = MainViewModelFactory(repository)
        viewModelSecond = ViewModelProvider(this, viewmodelFactory).get(MainViewModel::class.java)


    }

    private fun SetupRecyclerView() {
        recyitem = findViewById(R.id.recycler_selectedData)
        productSelected = ItemtSelectedAdapter(selectedProduct, listquantity)
        recyitem.layoutManager = LinearLayoutManager(this)
        recyitem.setHasFixedSize(true)
        recyitem.adapter = productSelected
    }

    private fun setupUI() {
        TvTaka = findViewById(R.id.totaltaka)
        etDeliveryAd = findViewById(R.id.outlinedTextField)
        btnSubmit = findViewById(R.id.submit)
        progressbar = findViewById(R.id.idLoadingPB)
        responseTv = findViewById(R.id.response)
        btnSubmit.setBackgroundColor(resources.getColor(R.color.black2))
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