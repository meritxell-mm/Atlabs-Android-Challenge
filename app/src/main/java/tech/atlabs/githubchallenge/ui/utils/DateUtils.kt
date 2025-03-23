package tech.atlabs.githubchallenge.ui.utils


import java.text.SimpleDateFormat
import java.util.*

fun String.toFormattedDate(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val date = parser.parse(this)
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        formatter.format(date!!)
    } catch (e: Exception) {
        this
    }
}