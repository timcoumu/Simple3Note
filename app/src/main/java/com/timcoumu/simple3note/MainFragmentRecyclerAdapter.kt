package com.timcoumu.simple3note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timcoumu.simple3note.model.Diary

//为recyclerview设置适配器
class MainFragmentRecyclerAdapter(private val data: ArrayList<Diary>?, context: Context) : RecyclerView.Adapter<MainFragmentRecyclerAdapter.ViewHolder>() {
    private val callback: Callback = context as Callback
    //创建内部类，viewholder它提供数据
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val details: TextView = itemView.findViewById(R.id.tv_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(data != null) {
            holder.title.text = data[position].title
            holder.details.text = data[position].details
        }
        //为列表每一项设置监听
        holder.itemView.setOnClickListener{
            //点击一项后，触发回调
            callback?.itemClicked(holder.title.text.toString())
        }
    }

    override fun getItemCount(): Int {
        if (data == null) {
            return 0
        }
        return data.size
    }
}