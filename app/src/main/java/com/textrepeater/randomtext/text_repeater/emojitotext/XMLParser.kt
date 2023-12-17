package com.textrepeater.randomtext.text_repeater.emojitotext

import android.util.Log
import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

class XMLParser(private val input: InputStream) {
    fun parse(): ArrayList<Translation?> {
        var entries = ArrayList<Translation?>()
        try {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(input, null)
            parser.nextTag()
            entries = readFeed(parser)
        } catch (id1: Exception) {
            Log.e(TAG, "Error parsing XML: $id1")
        }
        return entries
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readFeed(parser: XmlPullParser): ArrayList<Translation?> {
        val entries = ArrayList<Translation?>()
        parser.require(XmlPullParser.START_TAG, ns, "ldml")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "annotation") {
                val line = readAnnotation(parser)
                if (line != null) {
                    entries.add(line)
                }
            } else if (name != "annotations") {
                skip(parser)
            }
        }
        return entries
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readAnnotation(parser: XmlPullParser): Translation? {
        parser.require(XmlPullParser.START_TAG, ns, "annotation")
        var codePoint: String? = ""
        var type: String? = ""
        var description = ""
        codePoint = parser.getAttributeValue(null, "cp")
        type = parser.getAttributeValue(null, "type")
        if (type == null || type.compareTo("tts", ignoreCase = true) != 0) {
            parser.next()
            parser.next()
            parser.require(XmlPullParser.END_TAG, ns, "annotation")
            return null
        }
        description = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "annotation")
        description = replaceXMLInvalidChars(description)
        return Translation(codePoint, description)
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun replaceXMLInvalidChars(line: String): String {
        var line = line
        line = line.replace("\"", "&quot;")
        line = line.replace("'", "&apos;")
        line = line.replace("&", "&amp;")
        line = line.replace("<", "&lt;")
        line = line.replace(">", "&gt;")
        line = line.replace("...", "…")
        return line
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        check(parser.eventType == XmlPullParser.START_TAG)
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

    companion object {
        private const val TAG = "ETXMLParser"
        private val ns: String? = null
    }
}