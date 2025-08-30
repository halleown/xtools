package com.example.mylibrary.network.error

import com.example.mylibrary.network.core.NetworkResult
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

class ApiErrorMapper(private val gson: Gson = Gson()) {
	data class ErrorBody(val code: Int? = null, val message: String? = null)

	fun map(throwable: Throwable): NetworkResult.Error {
		return when (throwable) {
			is HttpException -> {
				val code = throwable.code()
				val message = parseErrorBody(throwable.response()?.errorBody()) ?: throwable.message()
				NetworkResult.Error(code, message, throwable)
			}
			is IOException -> NetworkResult.Error(null, "Network error", throwable)
			else -> NetworkResult.Error(null, throwable.message, throwable)
		}
	}

	private fun parseErrorBody(body: ResponseBody?): String? {
		return try {
			body?.charStream()?.use { reader ->
				val parsed = gson.fromJson(reader, ErrorBody::class.java)
				parsed?.message
			}
		} catch (_: Exception) {
			null
		}
	}
}