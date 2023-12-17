package com.textrepeater.randomtext.text_repeater.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.textrepeater.randomtext.text_repeater.R

class HorizontalAdapter(
    private var itemList: List<FontType>,
    var listener: ChangeFontListener
) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fonts_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        val tv = holder.textView
        tv.text = item.font
        holder.bind(item.font, item.fontFamily)
        tv.setOnClickListener { view: View? -> listener.changeFont(position) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.textView)
        }

        fun bind(text: String?, fontPath: String?) {
            textView.text = text
            val typeface = Typeface.createFromAsset(itemView.context.assets, fontPath)
            textView.typeface = typeface
        }
    }
}