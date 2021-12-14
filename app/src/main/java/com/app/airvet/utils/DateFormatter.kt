package com.app.airvet.utils

import android.content.Context
import timber.log.Timber
import java.text.DateFormat

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
    companion object {

        fun getStringToDateToFormeDateTimeNoMillisWithSecond(dateString: String): String {
            val locale: Locale = Locale.getDefault()
            return try {
                var date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale).parse(dateString)
                SimpleDateFormat("dd MMM,yyyy HH:mm:ss", locale).format(date)
            } catch (e: ParseException) {
                    Timber.e("dae ${e.localizedMessage}")
                ""
            }

        }

    }

}