package com.example.emartket.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class PostProductsItem(
    val imageUrl: String,
    val name: String,
    val price: Int
) : Parcelable