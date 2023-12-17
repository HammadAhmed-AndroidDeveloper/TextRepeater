package com.textrepeater.randomtext.text_repeater.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.textrepeater.randomtext.text_repeater.R
import com.textrepeater.randomtext.text_repeater.emojitotext.EmojiTranslatorActivity

class HomeActivity : AppCompatActivity() {

    private var text: LinearLayout? = null
    private var emoji: LinearLayout? = null
    private var textToEmoji: LinearLayout? = null
    private var randomTextGenerator: LinearLayout? = null
    private var boldLetters: LinearLayout? = null
    private var checkRepeatedLetters: LinearLayout? = null
    private var setFont: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        text = findViewById(R.id.text)
        emoji = findViewById(R.id.emoji)
        textToEmoji = findViewById(R.id.textToEmoji)
        boldLetters = findViewById(R.id.boldLetters)
        setFont = findViewById(R.id.setFont)
        checkRepeatedLetters = findViewById(R.id.checkRepeatedLetters)
        randomTextGenerator = findViewById(R.id.randomTextGenerator)
        text?.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    MainActivity::class.java
                )
            )
        }
        emoji?.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    EmojiRepeaterActivity::class.java
                )
            )
        }
        textToEmoji?.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    EmojiTranslatorActivity::class.java
                )
            )
        }
        randomTextGenerator?.setOnClickListener {
            startActivity(
                Intent(this@HomeActivity, RandomTextGenerator::class.java)
            )
        }
        setFont?.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    SetFontActivity::class.java
                )
            )
        }
        checkRepeatedLetters?.setOnClickListener {
            startActivity(
                Intent(this@HomeActivity, CheckRepeatedLettersActivity::class.java)
            )
        }
        boldLetters?.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    BoldFontActivity::class.java
                )
            )
        }
    }
}