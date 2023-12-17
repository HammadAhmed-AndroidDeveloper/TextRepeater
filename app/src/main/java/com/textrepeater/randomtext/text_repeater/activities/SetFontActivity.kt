package com.textrepeater.randomtext.text_repeater.activities

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.textrepeater.randomtext.text_repeater.R
import com.textrepeater.randomtext.text_repeater.adapter.ChangeFontListener
import com.textrepeater.randomtext.text_repeater.adapter.FontType
import com.textrepeater.randomtext.text_repeater.adapter.HorizontalAdapter

class SetFontActivity : AppCompatActivity() {
    
    private var recyclerView: RecyclerView? = null
    private var horizontalAdapter: HorizontalAdapter? = null
    private var itemList: MutableList<FontType>? = null
    private var et_input: TextInputEditText? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_font)
        recyclerView = findViewById(R.id.fontsRV)
        et_input = findViewById(R.id.et_input)
        itemList = ArrayList()
        itemList?.add(FontType("Anime", "font/anime.otf"))
        itemList?.add(FontType("Facttoria", "font/facttoria.otf"))
        itemList?.add(FontType("Hundergad", "font/hundergad.ttf"))
        itemList?.add(FontType("Kaglia", "font/kaglia.otf"))
        itemList?.add(FontType("Marghines", "font/marghines.ttf"))
        itemList?.add(FontType("Prodex", "font/prodex.ttf"))
        itemList?.add(FontType("Retrovintage", "font/retrovintage.otf"))
        itemList?.add(FontType("Spark", "font/spark.otf"))
        horizontalAdapter = HorizontalAdapter(
            itemList as ArrayList<FontType>,
            object : ChangeFontListener {
                override fun changeFont(position: Int) {
                    setFontType(itemList?.get(position)?.fontFamily)
                }

            }
        )
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = horizontalAdapter
    }

    private fun setFontType(which: String?) {
        Handler(Looper.getMainLooper()).post {
            et_input!!.typeface = Typeface.createFromAsset(
                assets, which
            )
        }
    }
}