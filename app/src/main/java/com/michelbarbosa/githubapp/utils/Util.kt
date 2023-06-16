package com.michelbarbosa.githubapp.utils

import com.michelbarbosa.githubapp.GeneralConstants.PATTERN_DD_MM_YYYY
import com.michelbarbosa.githubapp.GeneralConstants.PATTERN_YYYY_MM_DD
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object Util {

    fun normalizePatternDate(time: String?) =
        parseDateToddMMyyyy(PATTERN_YYYY_MM_DD, time, PATTERN_DD_MM_YYYY)

    private fun parseDateToddMMyyyy(
        inputPattern: String,
        time: String?,
        outputPattern: String
    ): String? {
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        var str: String? = null

        time?.let {
            try {
                inputFormat.parse(it)?.let { date ->
                    str = outputFormat.format(date)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        return str
    }
}