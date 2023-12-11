package com.example.myutil.utils

object ByteUtils {

    private const val HEX_LENGTH_8 = 8
    private const val HEX_PARSE_16 = 16
    private const val FF_LOCATION = 6

    fun toHex(data: ByteArray): String {
        val sb = StringBuilder(data.size * 2)
        for (i in data.indices) {
            var hex = Integer.toHexString(data[i].toInt())
            if (hex.length == 1) {
                // Append leading zero.
                sb.append("0")
            } else if (hex.length == HEX_LENGTH_8) {
                // Remove ff prefix from negative numbers.
                hex = hex.substring(FF_LOCATION)
            }
            sb.append(hex)
        }
        return lowerCase(sb.toString())
    }

    private fun lowerCase(str: String): String {
        return if (str.isEmpty()) {
            ""
        } else {
            str.lowercase()
        }
    }
}