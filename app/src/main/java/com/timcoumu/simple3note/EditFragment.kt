package com.timcoumu.simple3note

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.timcoumu.mydirary.database.Diary

class EditFragment : Fragment(){
    var et_title: EditText? = null
    var et_details: EditText? = null
    lateinit var contextM: Context
    override fun onAttach(context: Context) {
        this.contextM = context
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        et_title = view.findViewById<EditText>(R.id.et_title)
        et_details = view.findViewById<EditText>(R.id.et_content)
        return view
    }
    //从edit页面离开时保存数据
    fun saveEdit(): Diary {
        return Diary(et_title?.text.toString(), et_details?.text.toString())
    }
    //加载数据到edittext
    fun loadtoEdit(title: String, details: String){
        et_title!!.setText(title)
        et_details!!.setText(details)
    }
}