package features

import com.sun.javaws.exceptions.InvalidArgumentException
import java.time.LocalDateTime

private val dateTimeRegex = "(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})".toRegex()

fun String.toLocalDateTime(): LocalDateTime =
    dateTimeRegex.find(this)
        ?.groupValues
        ?.drop(1)
        ?.map(String::toInt)
        ?.let { LocalDateTime.of(it[0], it[1], it[2], it[3], it[4]) }
        ?: throw InvalidArgumentException(arrayOf("Invalid datetime : $this"))
