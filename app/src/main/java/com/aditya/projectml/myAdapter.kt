package com.aditya.projectml

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class myAdapter(val listData: ArrayList<myData>,val context:Activity) :RecyclerView.Adapter<myAdapter.myViewHolder>() {
    inner class myViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.myText)
        val img=itemView.findViewById<ImageView>(R.id.myImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        return myViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.text.text=listData[position].resData
        holder.img.setImageBitmap(listData[position].image)



        holder.itemView.setOnClickListener {

           val i=Intent(context,showDataAct::class.java)
            i.putExtra("img",listData[position].image)
            i.putExtra("text",listData[position].resData)
            context.startActivity(i)
        }
    }
}