package com.textrepeater.randomtext.text_repeater.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.textrepeater.randomtext.text_repeater.R

class EmojiRepeaterActivity : AppCompatActivity() {

    private var et_input: TextInputEditText? = null
    private var et_number: TextInputEditText? = null
    private var et_output: TextInputEditText? = null
    private var filledTextField3: TextInputLayout? = null
    private var lineCheck: SwitchCompat? = null
    private var btn_enable: Button? = null
    private var text = ""
    private var shareFrame: FrameLayout? = null
    private var copyFrame: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_input = findViewById(R.id.et_input)
        et_number = findViewById(R.id.numberOfTimesET)
        et_output = findViewById(R.id.textOutputET)
        lineCheck = findViewById(R.id.lineCheck)
        shareFrame = findViewById(R.id.shareFrame)
        copyFrame = findViewById(R.id.copyFrame)
        filledTextField3 = findViewById(R.id.filledTextField3)
        btn_enable = findViewById<View>(R.id.repeatTextBtn) as Button
        lineCheck?.setOnCheckedChangeListener { _, _ -> }
        btn_enable!!.setOnClickListener {
            val input = et_input?.text.toString()
            val tempNumber = et_number?.text.toString()
            if (tempNumber != "") {
                val number = et_number?.text.toString().toInt()
                text = ""
                for (i in 0 until number) {
                    text = if (lineCheck!!.isChecked) {
                        """$text$input

     """.trimIndent()
                    } else {
                        "$text$input "
                    }
                }
                filledTextField3?.visibility = View.VISIBLE
                et_output?.setText(text)
            }
        }
        copyFrame?.setOnClickListener {
            val text = et_output?.text.toString()
            if (text.isNotEmpty()) {
                copyText(text)
            } else Toast.makeText(this@EmojiRepeaterActivity, "No Data found", Toast.LENGTH_SHORT)
                .show()
        }
        shareFrame?.setOnClickListener {
            val text = et_output?.text.toString()
            if (text.isNotEmpty()) {
                shareText(text)
            } else Toast.makeText(this@EmojiRepeaterActivity, "No Data found", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun copyText(text: String?) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", text)
        clipboard.setPrimaryClip(clipData)
        Toast.makeText(this, "Copied to Clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        val chooser = Intent.createChooser(intent, "Share text")
        startActivity(chooser)
    }
}