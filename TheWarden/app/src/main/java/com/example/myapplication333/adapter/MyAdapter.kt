package com.example.myapplication333.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication333.R
import com.example.myapplication333.db.ProductObj

class MyAdapter(val context: Context, i: Int, l : List<ProductObj>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    lateinit var view : View
    private val n = i
    private val list : List<ProductObj> = l

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roll_no_tv : TextView = itemView.findViewById(R.id.roll_no_tv)
        val name_tv : TextView = itemView.findViewById(R.id.name_tv)
        val standart_tv : TextView = itemView.findViewById(R.id.standart_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(context).inflate(R.layout.rv_layout, parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roll_no_tv.text = list[position].getName()
        holder.name_tv.text = list[position].getArticle()
        holder.standart_tv.text = "${list[position].getPrice()}₽"
    }

    override fun getItemCount(): Int {
        Log.i("MSG:", "n = $n")
        return n
    }
}