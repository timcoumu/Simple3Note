package com.timcoumu.simple3note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timcoumu.simple3note.model.Diary

//主fragment
class MainFragment(showData: ArrayList<Diary>?) : Fragment() {
    lateinit var myView: View
    var recyclerView: RecyclerView? = null
    lateinit var adapter: MainFragmentRecyclerAdapter
    lateinit var contextM: Context
    var showData = showData
    override fun onAttach(context: Context) {
        this.contextM = context
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_main, container, false)
        adapter = MainFragmentRecyclerAdapter(showData, contextM)
        recyclerView = myView.findViewById<RecyclerView>(R.id.recyclerview_main)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
        return myView
    }
}