package tech.atlabs.githubchallenge.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns


fun Context.openUrl(url: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
        this.startActivity(it)
    }
}

fun isLink(value: String): Boolean {
    return Patterns.WEB_URL.matcher(value).matches()
}