package com.reo.running.runningactivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.runningactivity.R

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.tv_item_model)
}