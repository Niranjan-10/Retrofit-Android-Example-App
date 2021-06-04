package com.example.retrofitoperationapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
):Parcelable