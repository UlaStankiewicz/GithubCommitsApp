package pl.kserocki.githubapp.db.callback

import android.util.Log
import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {
    private val code: Int
    val body: T?
    val errorMessage: String?

    val isSuccessful: Boolean
        get() = code in 200..300

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message.toString()
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            response.errorBody()?.let {
                try {
                    message = response.errorBody()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            message?.apply {
                if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
                    message = response.message()
                }
            }
            errorMessage = message
            body = null
        }
    }
}
