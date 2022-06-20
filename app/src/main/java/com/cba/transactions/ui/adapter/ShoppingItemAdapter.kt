package com.cba.transactions.ui.adapter

import android.annotation.SuppressLint
import com.cba.transactions.R
import com.cba.transactions.base.RecyclerBaseAdapter
import com.cba.transactions.data.local.entry.ShoppingItem

class ShoppingItemAdapter constructor(var list: List<ShoppingItem>) :
    RecyclerBaseAdapter<ShoppingItem>() {



    override fun getDataAtPosition(position: Int): ShoppingItem {
        return list[position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        return R.layout.item_shopping
    }

    override fun getItemCount(): Int {
        return list.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(it: List<ShoppingItem>?) {
        if (it != null) {
            this.list = it
            notifyDataSetChanged()
        }

    }

    fun shoppingItems(pos: Int) =list[pos]


}