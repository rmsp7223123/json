package com.example.json

import org.json.JSONArray

data class PersonData(
    val name : String,
    val age : Int,
    val from : String,
    val education : JSONArray
);