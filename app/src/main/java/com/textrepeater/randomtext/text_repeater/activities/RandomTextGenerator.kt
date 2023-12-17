package com.textrepeater.randomtext.text_repeater.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText
import com.textrepeater.randomtext.text_repeater.R
import java.util.Random

class RandomTextGenerator : AppCompatActivity(), View.OnClickListener {
    private var upperCase: SwitchCompat? = null
    private var lowerCase: SwitchCompat? = null
    private var numeric: SwitchCompat? = null
    private var symbols: SwitchCompat? = null
    private var passwordEditTExt: TextInputEditText? = null
    private var passwordLength: EditText? = null
    private var generatePassword: AppCompatButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_generator)
        generatePassword = findViewById(R.id.generatePassword)
        passwordEditTExt = findViewById(R.id.et_input)
        upperCase = findViewById(R.id.upperCasePassword)
        lowerCase = findViewById(R.id.lowerCasePassword)
        numeric = findViewById(R.id.numberCasePassword)
        symbols = findViewById(R.id.symbolCasePassword)
        passwordLength = findViewById(R.id.passwordLength)
        generatePassword?.setOnClickListener(this)
    }

    private fun getPassword(which: String): String {
        val password = StringBuilder()
        val s = passwordLength!!.text.toString()
        var n = 8
        if (s.isNotEmpty()) {
            n = passwordLength!!.text.toString().toInt()
        }
        for (i in 0 until n) {
            password.append(which[Random().nextInt(which.length)])
        }
        return password.toString()
    }

    private fun generatePassword(which: String?): String {
        return when (which) {
            "onlyAplhas" -> {
                getPassword(ALPHAS)
            }

            "numsAlphas" -> {
                getPassword(AlphasNums)
            }

            "nums" -> {
                getPassword(numbers)
            }

            "numsAlphasChars" -> {
                getPassword(AlphasNumsChars)
            }

            "chars" -> {
                getPassword(chars)
            }

            "lower" -> {
                getPassword(lower)
            }

            "AlphaNums" -> {
                getPassword(AlphaNums)
            }

            "UppLowerChars" -> {
                getPassword(UppLowerChars)
            }

            "UpperNumericChars" -> {
                getPassword(UpperNumericChars)
            }

            "lowerNumericChars" -> {
                getPassword(lowerNumericChars)
            }

            "upperLower" -> {
                getPassword(upperLower)
            }

            "upperNumeric" -> {
                getPassword(upperNumeric)
            }

            "upperChars" -> {
                getPassword(upperChars)
            }

            "lowerChars" -> {
                getPassword(lowerChars)
            }

            "lowerNumeric" -> {
                getPassword(lowerNumeric)
            }

            "lowerUpper" -> {
                getPassword(lowerUpper)
            }

            else -> {
                getPassword(CHARS)
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.generatePassword) {
            if (upperCase!!.isChecked && lowerCase!!.isChecked && numeric!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("CHARS"))
            } else if (upperCase!!.isChecked && lowerCase!!.isChecked && numeric!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("AlphaNums"))
            } else if (upperCase!!.isChecked && lowerCase!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("UppLowerChars"))
            } else if (upperCase!!.isChecked && numeric!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("UpperNumericChars"))
            } else if (lowerCase!!.isChecked && numeric!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("lowerNumericChars"))
            } else if (upperCase!!.isChecked && lowerCase!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("upperLower"))
            } else if (upperCase!!.isChecked && numeric!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("upperNumeric"))
            } else if (upperCase!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("upperChars"))
            } else if (lowerCase!!.isChecked && upperCase!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("lowerUpper"))
            } else if (lowerCase!!.isChecked && numeric!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("lowerNumeric"))
            } else if (lowerCase!!.isChecked && symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("lowerChars"))
            } else if (upperCase!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("onlyAplhas"))
            } else if (lowerCase!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("lower"))
            } else if (numeric!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("nums"))
            } else if (symbols!!.isChecked) {
                passwordEditTExt!!.setText(generatePassword("nums"))
            }
        }
    }

    companion object {
        private const val CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=+{}[]<>,.?"
        private const val ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        private const val lower = "abcdefghijklmnopqrstuvwxyz"
        private const val lowerChars = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()-=+{}[]<>,.?"
        private const val lowerNumeric = "abcdefghijklmnopqrstuvwxyz0123456789"
        private const val lowerUpper = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        private const val upperLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        private const val upperNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        private const val upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=+{}[]<>,.?"
        private const val AlphasNums = "abcdefghijklmnopqrstuvwxyz0123456789"
        private const val AlphasNumsChars =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=+{}[]<>,.?"
        private const val UppLowerChars =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-=+{}[]<>,.?"
        private const val UpperNumericChars =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=+{}[]<>,.?"
        private const val lowerNumericChars =
            "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=+{}[]<>,.?"
        private const val AlphaNums = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        private const val numbers = "0123456789"
        private const val chars = "!@#$%^&*()-=+{}[]<>,.?"
    }
}