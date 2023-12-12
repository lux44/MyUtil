package com.example.myutil.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

object TimeUtils {

        //밀리초 일 경우
        fun diffTimeWithCurrentTime(time: Long): String {
            if (time == 0L) return ""
            //현재 시간
            var timeString = "Unknown"
            try {
                val timeZone = TimeZone.getTimeZone("Asia/Seoul")
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                dateFormat.timeZone = timeZone
                val currentTime = dateFormat.format(Date())
                val currentDate =dateFormat.parse(currentTime).time
                val diffTime = currentDate - (time * 1000)

                val hours = (diffTime / (1000 * 60 * 60)).toInt()
                val mins = ((diffTime / (1000 * 60)) % 60).toInt()
                val sec = ((diffTime / 1000) % 60).toInt()

                if (hours >= 24) {
                    timeString = "${hours / 24} 일 전"
                } else if (hours > 0) {
                    timeString = "${hours} 시간 전"
                } else if (mins > 0) {
                    timeString = "${mins} 분 전"
                } else {
                    timeString = "방금 전"
                }
            } catch (e: Exception) {

            }
            return timeString
        }


        fun changeTimeStringFormat(
            originDate: String,
            changeFormat: String,
            originFormat: String = "yyyy-MM-dd'T'HH:mm:ss"
        ) :String {
            val oldFormat = SimpleDateFormat(originFormat)
            //시간선 맞추기
            oldFormat.timeZone = TimeZone.getDefault()
            //바꿀 포맷
            val currentYearFormat = SimpleDateFormat(changeFormat)
            val oldDate = oldFormat.parse(originDate)
            var newDate=originDate
            try{
                newDate = currentYearFormat.format(oldDate)
            }catch (e :Exception){

            }
            return newDate
        }

        fun getCurrentTime(timezone:String):Long{
            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat()
            cal.time = Date()
            if(timezone.isNotEmpty()) {
                sdf.timeZone = TimeZone.getTimeZone(timezone)
            }
            return sdf.parse(sdf.format(cal.time)).time
        }

        fun yearMonthDayDotString(time: Long): String {
            return getLocalDateTimeFromEpochMilli(time).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        }

        fun yearMonthDayDotStringFromDate(date: String): String {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        }

        fun getYearMonthFromLocalDateTime(localDateTime: LocalDateTime): String {
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM"))
        }
        fun getYearMonthFromLocalDateTime(yearMonth: YearMonth): String {
            return yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"))
        }
        fun getYearMonthFromLocalDateTimeKorean(localDateTime: LocalDateTime): String {
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월"))
        }
        fun getYearMonthFromLocalDateTimeKorean(yearMonth: YearMonth): String {
            return yearMonth.format(DateTimeFormatter.ofPattern("yyyy년 MM월"))
        }

        // such as '2011-12-03' to epoch milli.
        fun getEpochMilliTimeFromDate(date: String): Long {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }

        // Returns the ISO date formatter that prints/parses a date-time without an offset, such as '2011-12-03T10:15:30'
        fun getParsedLocalDateTime(date: String?): LocalDateTime {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }

        fun getCurrentLocalDateTime(): LocalDateTime {
            return LocalDateTime.now(ZoneId.systemDefault())
        }

        fun getLocalDateTimeFromEpochMilli(time: Long): LocalDateTime {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault())
        }


}