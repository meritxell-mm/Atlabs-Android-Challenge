package tech.atlabs.githubchallenge.ui.utils

import android.content.Context

interface ResourceProvider {
    fun getString(resId: Int): String
}

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String = context.getString(resId)
}