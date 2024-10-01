package com.timcoumu.simple3note

import com.timcoumu.mydirary.database.Diary

interface Callback {
    fun itemClicked(title:String)
}