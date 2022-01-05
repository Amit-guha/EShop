package com.example.e_marketapplication.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_marketapplication.R
import com.example.emartket.data.model.PostProductsItem
import kotlin.collections.ArrayList

class ProductAdapter2(
    //  private val listener: onCardLisitiner,
    //private val Onlistener: OnItemClickListener
) :

    RecyclerView.Adapter<ProductAdapter2.DataViewHolder>() {
    private var users = emptyList<PostProductsItem>()
    private var SelectedProductList = arrayListOf<PostProductsItem>()


    // View.OnClickListener
    inner class DataViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var quantity: Int = 1

        var name = itemview.findViewById<TextView>(R.id.tv_name)
        var priceitem = itemview.findViewById<TextView>(R.id.tv_price)
        var imageView = itemView.findViewById<ImageView>(R.id.img_click)

        var addbutton = itemview.findViewById<Button>(R.id.btnadd)
        var plusbutton = itemview.findViewById<ImageButton>(R.id.btnPlus)
        var minusbutton = itemview.findViewById<ImageButton>(R.id.btnMinus)
        val tvshow: TextView = itemview.findViewById(R.id.tv_count)
        val totalprice: TextView = itemview.findViewById(R.id.tv_totalPrice)

        /*  init {
              itemview.setOnClickListener(this)
              addbutton.setOnClickListener {
                  val position = adapterPosition
                 // if (position != RecyclerView.NO_POSITION) {
                      Onlistener.onItemClick(position)



                 // }
              }
          }*/

        fun bind(postProductsItem: PostProductsItem) {
            itemView.apply {
                name.text = postProductsItem.name
                priceitem.text = postProductsItem.price.toString()

                Glide.with(context)
                    .load(postProductsItem.imageUrl)
                    .placeholder(R.drawable.progress_bar)
                    .error(R.drawable.ic_error)
                    .centerCrop()
                    .into(imageView)

                totalprice.setText((postProductsItem.price * 1).toString())


                plusbutton.setOnClickListener {
                    quantity++
                    totalprice.setText((postProductsItem.price * quantity).toString())
                    displayQunatity()
                }


                //Decrase the value when presssing the minusbutton
                minusbutton.setOnClickListener {
                    if (quantity == 1) {
                        Toast.makeText(
                            context.applicationContext, "Can not decrase quantity",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        quantity--
                        totalprice.setText((postProductsItem.price * quantity).toString())
                        displayQunatity()

                    }

                }

                addbutton.setOnClickListener {
                    /*   addbutton.setText("Already in Cart")
                       addbutton.isEnabled = false
                       addbutton.setTextColor(resources.getColor(R.color.white))
                       addbutton.setBackgroundColor(resources.getColor(R.color.black2))
   */
                    Toast.makeText(context,"${name.text.toString()} to cart",Toast.LENGTH_SHORT).show()
                    val intent = Intent("custom-message");
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    intent.putExtra("name", name.text.toString());
                    intent.putExtra("price", totalprice.text.toString())
                    intent.putExtra("Imgeurl", postProductsItem.imageUrl.toString())
                    intent.putExtra("totalquantity",tvshow.text.toString())
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
                }


            }

            /*   val intent= Intent()
               intent.putExtra("name",name.text.toString())
               Log.d("anything",name.text.toString())
               listener.cardLisitiner(intent)
   */

        }

        //Set text in textview
        private fun displayQunatity() {
            tvshow.setText(quantity.toString())
        }


        /*     //onitemclik
             override fun onClick(p0: View?) {
                 val position = adapterPosition
                 if (position != RecyclerView.NO_POSITION) {
                     Onlistener.onItemClick(position)
                 }
             }
     */


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setData(newList: List<PostProductsItem>) {
        users = newList
        notifyDataSetChanged()
    }

    //create an iterface

    /*  interface onCardLisitiner{
          fun cardLisitiner(intent : Intent)
      }*/

    /*   interface OnItemClickListener {
           fun onItemClick(position: Int)
       }*/

}