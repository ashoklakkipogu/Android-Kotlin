package com.cba.transactions.databinding

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cba.transactions.R
import kotlin.math.min


class DataBindingAdapter {
    companion object {
        @BindingAdapter("account")
        @JvmStatic
        fun setAccount(view: TextView, text: String?) {
            text?.let {
                val sb = StringBuilder()
                var index = 0;
                while (index < text.length) {
                    sb.append(text.substring(index, min(index + 4, text.length)) + " ")
                    index += 4
                }
                view.text = sb.toString()
            }
        }

        @BindingAdapter("html")
        @JvmStatic
        fun setHtml(view: TextView, text: String?) {
            text?.let {
                view.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(text)
                }

            }
        }

        @BindingAdapter("drawable")
        @JvmStatic
        fun setDrawable(view: ImageView, text: String?) {
            try {
                val ctx = view.context
                val category = "icon_category_$text"
                val id: Int = ctx.resources.getIdentifier(category, "drawable", ctx.packageName)
                view.setImageDrawable(ContextCompat.getDrawable(view.context, id));
            } catch (e: Exception) {
            }

        }

        @SuppressLint("CheckResult")
        @BindingAdapter("image")
        @JvmStatic
        fun setImage(view: ImageView, uri: String?) {
            try {
                Glide.with(view.context).load(uri)
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(view)
            } catch (e: Exception) {
            }

        }
    }


}