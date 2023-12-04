package com.example.json

import io.realm.kotlin.types.RealmObject

class Dog(val id: Long,
          val name:String ="",
          var age: Int=0) : RealmObject {
}