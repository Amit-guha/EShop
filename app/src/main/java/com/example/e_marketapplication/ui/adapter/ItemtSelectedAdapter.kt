package com.example.e_marketapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_marketapplication.R
import com.example.emartket.data.model.PostProductsItem

class ItemtSelectedAdapter(private var userItem: ArrayList<PostProductsItem>,
                           private val listquantity: ArrayList<String>) :
    RecyclerView.Adapter<ItemtSelectedAdapter.TotalViewHolder>() {

   inner class TotalViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

       val image : ImageView = itemview.findViewById(R.id.img_click)
       val pname =itemview.findViewById<TextView>(R.id.p_name)
       val pquantity= itemview.findViewById<TextView>(R.id.tv_qunatity)
       val pPrice =itemview.findViewById<TextView>(R.id.p_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalViewHolder {
        return TotalViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_prodct_show, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) {
        val currentitem = userItem[position]
        val quantityItem= listquantity[position]

        Glide.with(holder.itemView.context)
            .load(currentitem.imageUrl)
            .placeholder(R.drawable.progress_bar)
            .error(R.drawable.ic_error)
            .centerCrop()
            .into(holder.image)

        holder.pname.text=currentitem.name.toString()
        holder.pPrice.text=currentitem.price.toString()
        holder.pquantity.text=quantityItem
    }

    override fun getItemCount(): Int {
        return userItem.size
    }
}