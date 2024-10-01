package com.timcoumu.simple3note.Controller

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.util.query
import com.timcoumu.mydirary.database.Diary
import com.timcoumu.mydirary.database.DiaryDatabase


class Controller(context: Context) {
    val context = context
    val dao = DiaryDatabase.getDatabase(context).dirarydao()
    //保存到数据库
    fun savetoDatabase(title: String, details: String): Boolean{
        if (dao.loadDiraryByTitle(title).size == 0 && title!= "") {
            dao.insertDirary(Diary(title, details))
            return true
        }else{
            Toast.makeText(context, "标题已存在或为空！", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    //从主fragment到editfragment加载数据到编辑页（同时删除数据库中数据）
    fun loadOneData(title: String): com.timcoumu.simple3note.model.Diary{
        val querydata = dao.loadDiraryByTitle(title)
        val data = com.timcoumu.simple3note.model.Diary(querydata[0].title, querydata[0].details)
        return data
    }
    //从主fragment到editfragment删除数据库中一条
    fun deleteOneData(title: String){
        dao.deleteDirayByTitle(title)
    }
    //加载到主fragment中的数据,数据库中所有数据
    fun loadtoMainfragment(): ArrayList<com.timcoumu.simple3note.model.Diary>? {
        val querydata = dao.loadAllDiraries()
        val data = ArrayList<com.timcoumu.simple3note.model.Diary>()
        for (item in querydata){
            data?.add(com.timcoumu.simple3note.model.Diary(item.title, item.details))
        }
        return data
    }
    //删除编辑页面数据，退出app（不保存当前内容）
    fun backPressure(){
        AlertDialog.Builder(context)
            .setMessage("是否退出")
            .setTitle("退出")
            .setPositiveButton("退出应用") { dialog, id ->
            }
            .setNegativeButton("不退出") { dialog, id ->
                throw Exception()
            }
            .show()
    }
}