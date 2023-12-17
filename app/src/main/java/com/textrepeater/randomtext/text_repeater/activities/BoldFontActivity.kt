package com.textrepeater.randomtext.text_repeater.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.textrepeater.randomtext.text_repeater.R

class BoldFontActivity : AppCompatActivity() {

   private var et_input: TextInputEditText? = null
    private var boldText: TextView? = null
    private var boldTextBtn: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bold_font)
        et_input = findViewById(R.id.et_input)
        boldTextBtn = findViewById(R.id.boldTextBtn)
        boldText = findViewById(R.id.boldText)
        boldTextBtn?.setOnClickListener {
            val text = et_input?.text.toString().trim { it <= ' ' }
            val sb = StringBuilder()
            for (element in text) {
                sb.append(element).append("\n")
            }
            boldText?.text = sb.toString()
        }
    }
}