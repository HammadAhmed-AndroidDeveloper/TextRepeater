package com.textrepeater.randomtext.text_repeater.emojitotext

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.textrepeater.randomtext.text_repeater.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.io.IOException

class EmojiTranslatorActivity : AppCompatActivity() {

    private lateinit var pd: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)
        val button = findViewById<AppCompatButton>(R.id.button)

        pd = ProgressDialog(this@EmojiTranslatorActivity)
        pd.setMessage("Translating.....!")
        button.setOnClickListener {
            pd.show()
            val input = findViewById<TextInputEditText>(R.id.et_input)
            val output = findViewById<TextView>(R.id.resultText)
            val input_text = input.text.toString()
            generateTranslations()
            val translated_text =
                EmojiToText.translateEmoji(this@EmojiTranslatorActivity, input_text)
            output.text = translated_text
        }
    }

    private fun generateTranslations() {
        CoroutineScope(Dispatchers.IO).launch {
            var translation_file: ArrayList<Translation?>
            var translation_file_derived: ArrayList<Translation?>?
            try {
                val root = File(Environment.getExternalStorageDirectory(), "EmojiTranslations")
                if (!root.exists()) {
                    root.mkdirs()
                }
                val a = assets
                val languages = a.list(ASSETS_BASIC) ?: return@launch
                for (lang in languages) {
                    var `in` = assets.open("$ASSETS_BASIC/$lang")
                    var xml = XMLParser(`in`)
                    translation_file = xml.parse()
                    `in`.close()
                    try {
                        `in` = assets.open("$ASSETS_DERIVED/$lang")
                        xml = XMLParser(`in`)
                        translation_file_derived = xml.parse()
                        translation_file.addAll(translation_file_derived)
                        `in`.close()
                        withContext(Dispatchers.Main) {
                            Handler(Looper.getMainLooper()).post {

                                pd.dismiss()
                            }
                        }
                    } catch (e: IOException) {
                        Log.i(TAG, "Has no derived strings")
                    }
                    try {
                        val folder = File(root, lang.substring(0, lang.length - 4))
                        if (!folder.exists()) {
                            folder.mkdirs()
                        }
                        val tFile = File(folder, TRANSLATEDXML)
                        val writer = FileWriter(tFile)
                        writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?> \n")
                        writer.append(COPYRIGHTMESSAGE)
                        writer.append("<resources> \n")
                        for (t in translation_file) {
                            writer.append("\t")
                            writer.append(t.toString())
                            writer.append("\n")
                        }
                        writer.append("</resources> \n")
                        writer.flush()
                        writer.close()


                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    translation_file.clear()
                }
            } catch (id: Exception) {
                Log.e("TEST", "Error opening voices XML file: $id")
            }
        }
    }

    companion object {
        private const val TAG = "ETAct"
        private const val ASSETS_BASIC = "basic"
        private const val ASSETS_DERIVED = "derived"
        private const val TRANSLATEDXML = "strings-emoji-descriptions.xml"
        private const val COPYRIGHTMESSAGE =
            "<!-- Copyright © 1991-2018 Unicode, Inc. \nFor terms of use, see http://www.unicode.org/copyright.html \nUnicode and the Unicode Logo are registered trademarks of Unicode, Inc. in the U.S. and other countries. \nCLDR data files are interpreted according to the LDML specification (http://unicode.org/reports/tr35/) \n \n Warnings: All cp values have U+FE0F characters removed. See /annotationsDerived/ for derived annotations.  \n-->\n"
    }
}