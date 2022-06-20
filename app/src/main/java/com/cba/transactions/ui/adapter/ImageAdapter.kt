package com.cba.transactions.ui.adapter

import android.annotation.SuppressLint
import com.cba.transactions.R
import com.cba.transactions.base.RecyclerBaseAdapter

class ImageAdapter constructor(var list: List<String>) :
    RecyclerBaseAdapter<String>() {


    override fun getDataAtPosition(position: Int): String {
        return list[position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        return R.layout.item_image
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val url = list[position]
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(url)
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(it: List<String>?) {
        if (it != null) {
            this.list = it
            notifyDataSetChanged()
        }

    }

    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


}