package com.timcoumu.mydirary.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey
    var title: String,
    var details: String
)
