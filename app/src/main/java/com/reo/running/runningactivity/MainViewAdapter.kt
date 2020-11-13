package com.reo.running.runningactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainViewAdapter(
        private val list: List<ItemModel>,
        private val listener: ListListener
) : RecyclerView.Adapter<MainViewHolder>() {

    interface ListListener {
        fun onClickItem(tappedView: View, itemModel: ItemModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MainViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.part_item_model, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textView.findViewById<TextView>(R.id.tv_item_model).text = list[position].text
        holder.textView.setOnClickListener {
            listener.onClickItem(it, list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}