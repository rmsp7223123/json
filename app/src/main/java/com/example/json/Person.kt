package com.example.json

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class Person(val name: String, val age: Int, val city: String, val email: String);