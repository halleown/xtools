package com.example.mylibrary.network.core

sealed class NetworkResult<out T> {
	data class Success<T>(val data: T) : NetworkResult<T>()
	data class Error(val code: Int?, val message: String?, val throwable: Throwable? = null) : NetworkResult<Nothing>()
	object Loading : NetworkResult<Nothing>()
}