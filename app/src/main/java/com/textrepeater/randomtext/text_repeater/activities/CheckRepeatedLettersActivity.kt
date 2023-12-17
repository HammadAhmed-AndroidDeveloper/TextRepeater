package com.textrepeater.randomtext.text_repeater.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.textrepeater.randomtext.text_repeater.R
import java.util.Locale

class CheckRepeatedLettersActivity : AppCompatActivity() {
    private var et_input: TextInputEditText? = null
    private var repeatedText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_repeated_letters)
        et_input = findViewById(R.id.et_input)
        repeatedText = findViewById<View>(R.id.repeatedText) as TextView
        et_input?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                getAllRepeatedLetters(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    @SuppressLint("SetTextI18n")
    fun getAllRepeatedLetters(text: String) {
        var txt = text
        val letterFrequencies = HashMap<Char, Int>()
        val stringBuilder = StringBuilder()
        txt = txt.lowercase(Locale.getDefault())
        if (txt.isNotEmpty()) {
            for (element in txt) {
                if (letterFrequencies.containsKey(element)) {
                    letterFrequencies[element] = letterFrequencies[element]!! + 1
                } else {
                    letterFrequencies[element] = 1
                }
            }
            for (letter in letterFrequencies.keys) {
                if (letterFrequencies[letter]!! > 1) {
                    if (letter != ' ') {
                        stringBuilder.append(letter).append(", ")
                    }
                    Log.d("repeatedLetter", "getAllRepeatedLetters: $stringBuilder")
                }
            }
            repeatedText!!.text = stringBuilder
        }
    }
}