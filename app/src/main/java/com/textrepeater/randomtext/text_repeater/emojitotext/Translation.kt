package com.textrepeater.randomtext.text_repeater.emojitotext

class Translation internal constructor(codepoint: String, description: String) {
    private val mName: String
    private val mDescription: String

    init {
        val name = StringBuilder()
        name.append(namePrefix)
        var i = 0
        while (i < codepoint.length) {
            val current = codepoint.codePointAt(i)
            val charsToSkip = Character.charCount(current) - 1
            if (current != 8205) {
                val mCodepoints: ArrayList<Int?> = ArrayList()
                mCodepoints.add(current)
                name.append("_")
                name.append(Integer.toHexString(codepoint.codePointAt(i)))
            }
            i += charsToSkip
            ++i
        }
        mName = name.toString()
        mDescription = description
    }

    override fun toString(): String {
        return "<string name=\"$mName\">$mDescription</string>"
    }

    companion object {
        const val namePrefix = "spoken_emoji"
    }
}