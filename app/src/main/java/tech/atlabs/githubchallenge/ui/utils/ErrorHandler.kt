package tech.atlabs.githubchallenge.ui.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import retrofit2.HttpException
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.utils.ErrorType
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ErrorHandler @Inject constructor(private val resourceProvider: ResourceProvider) {

    fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is IOException -> resourceProvider.getString(R.string.error_network)
            is TimeoutException -> resourceProvider.getString(R.string.error_timeout)
            is ErrorType.UserErrorType.UserNotFound -> resourceProvider.getString(R.string.error_user_not_found)
            is HttpException -> when (error.code()) {
                400 -> resourceProvider.getString(R.string.error_bad_request)
                401 -> resourceProvider.getString(R.string.error_not_authorized)
                403 -> resourceProvider.getString(R.string.error_access_denied)
                404 -> resourceProvider.getString(R.string.error_user_not_found) //just in case
                500 -> resourceProvider.getString(R.string.error_server)
                else -> resourceProvider.getString(R.string.error_server)
            }

            else -> {
                Log.e("ErrorHandler", "Unexpected error", error)
                resourceProvider.getString(R.string.error_unknown)
            }
        }
    }
}