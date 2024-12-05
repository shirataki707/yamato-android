package jp.shirataki707.yamato.core.common.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateFormatUtils {
    fun formatDate(input: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME

        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return ZonedDateTime.parse(input, inputFormatter).format(outputFormatter)
    }
}
