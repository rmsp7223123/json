package com.example.json

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class Todo(
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var date: Long = 0
) : RealmObject {
}