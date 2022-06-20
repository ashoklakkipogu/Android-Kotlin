package com.cba.transactions.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.cba.transactions.R
import com.cba.transactions.base.RecyclerBaseAdapter
import com.cba.transactions.data.model.Transaction
import com.cba.transactions.databinding.DateRowBinding
import com.cba.transactions.databinding.TransactionRowBinding
import com.cba.transactions.ui.activity.MainActivity
import com.cba.transactions.ui.model.TransactionHistoryData


class TransactionAdapter constructor(
    var mActivity: MainActivity?,
    var list: List<TransactionHistoryData>
) :
    RecyclerBaseAdapter<TransactionHistoryData?>() {
    private var fontSize: Float = 2F

    companion object {
        const val DATE_VIEW = 0
        const val TRANSACTION_VIEW = 1
    }

    override fun getDataAtPosition(position: Int): TransactionHistoryData {
        return list[position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        if (viewType == DATE_VIEW) {
            return R.layout.date_row
        } else if (viewType == TRANSACTION_VIEW) {
            return R.layout.transaction_row
        }
        return R.layout.transaction_row
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: List<TransactionHistoryData>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (getItemViewType(position) == TRANSACTION_VIEW) {
            val view = holder.binding as TransactionRowBinding
            val obj = list[position]
            val transactionObj = obj.transaction
            if (transactionObj!!.isPending) {
                val text1 = "<b>PENDING:</b>"
                view.desc = text1 + " " + transactionObj.description
            } else {
                view.desc = transactionObj.description
            }
            view.description.textSize = 12F + fontSize
            view.ammount.textSize = 14F + fontSize
            view.container.setOnClickListener {
                mActivity?.onClickTransaction(obj.transaction)
            }

        }else if (getItemViewType(position) == DATE_VIEW){
            val view = holder.binding as DateRowBinding
            view.date.textSize = 14F + fontSize
            view.day.textSize = 14F + fontSize
        }
    }

    fun onClick(view: View, obj: Transaction) {
        //mContext?.onClickItem(obj)
        //mContext?.onclickChapter(chaptertId)
    }

    fun onClickDelete(view: View, entry: Transaction) {
        //mContext?.deleteRow(entry)
    }

    override fun getItemViewType(position: Int): Int {
        val obj = list[position]
        return if (obj.isDate) {
            DATE_VIEW
        } else {
            TRANSACTION_VIEW
        }
    }

    fun updateFontSize(fontS: Float) {
        fontSize = fontS
        notifyDataSetChanged()
    }

}